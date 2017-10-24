package com.scvh.apps.util;

import com.scvh.apps.util.pars.ParamsBuilder;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CLIParser {

	private Options getOptions() {
		Option string = Option.builder("s").hasArg().longOpt("string").desc("Input string").build();
		Option help = Option.builder("h").longOpt("help").desc("Help").build();
		Option diff = Option.builder("d").longOpt("diff").desc("Show diff").build();
		Option colors = Option.builder("c").longOpt("color").desc("Colorize output").build();
		return new Options().addOption(string).addOption(help).addOption(diff).addOption(colors);
	}

	public ParamsBuilder parseInput(String[] args) {
		try {
			Options options = getOptions();
			CommandLine cli = new DefaultParser().parse(options, args);
			if (cli.hasOption("h")) {
				printHelpAndExit(options);
			}
			return new ParamsBuilder().setInput(cli.getOptionValue("s")).setDiff(cli.hasOption("d"))
					.setColored(cli.hasOption("c"));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void printHelpAndExit(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("formatter-cli", getOptions());
		System.exit(0);
	}

}
