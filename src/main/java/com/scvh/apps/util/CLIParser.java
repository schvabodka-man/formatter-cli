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
		Option ansiNew = Option.builder("n").longOpt("ansi-new").desc("Ansi char for new stuff in diff").build();
		Option ansiOld = Option.builder("o").longOpt("ansi-old").desc("Ansi char for old stuff in diff").build();
		return new Options().addOption(string).addOption(help).addOption(diff).addOption(colors).addOption(ansiNew).addOption(ansiOld);
	}

	public ParamsBuilder parseInput(String[] args) {
		try {
			Options options = getOptions();
			CommandLine cli = new DefaultParser().parse(options, args);
			if (cli.hasOption("h")) {
				printHelpAndExit(options);
			}
			ParamsBuilder builder = new ParamsBuilder().setInput(cli.getOptionValue("s")).setDiff(cli.hasOption("d"))
					.setColored(cli.hasOption("c"));

			//TODO Replace to macro or some shit
			if (cli.hasOption("n")) {
				builder.setAnsiNew(cli.getOptionValue("n"));
			}
			if (cli.hasOption("o")) {
				builder.setAnsiOld(cli.getOptionValue("o"));
			}

			return builder;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void printHelpAndExit(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("formatter-cli", options);
		System.exit(0);
	}

}
