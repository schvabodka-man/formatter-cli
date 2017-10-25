package com.scvh.apps.util;

import com.scvh.apps.util.pars.ParamsBuffer;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CLIParser {

	private Options getOptions() {
		Option string = Option.builder("s").required().hasArg().longOpt("string").desc("Input string").build();
		Option help = Option.builder("h").longOpt("help").desc("Help").build();
		Option diff = Option.builder("d").longOpt("diff").desc("Show diff").build();
		Option colors = Option.builder("c").longOpt("color").desc("Colorize output").build();
		Option interactive = Option.builder("i").longOpt("interactive").desc("Run app interactively").build();
		Option ansiNew = Option.builder("an").hasArg().longOpt("ansi-new").desc("Ansi char for new stuff in diff").build();
		Option ansiOld = Option.builder("ao").hasArg().longOpt("ansi-old").desc("Ansi char for old stuff in diff").build();
		Option charNew = Option.builder("cn").hasArg().longOpt("char-new").desc("Char to show for new stuff in diff").build();
		Option charOld = Option.builder("co").hasArg().longOpt("char-old").desc("Char to show for old stuff in diff").build();
		return new Options().addOption(string).addOption(help).addOption(diff).addOption(colors)
				.addOption(ansiNew).addOption(ansiOld).addOption(charOld).addOption(charNew).addOption(interactive);
	}

	public ParamsBuffer parseInput(String[] args) {
		try {
			Options options = getOptions();
			CommandLine cli = new DefaultParser().parse(options, args);
			if (cli.hasOption("h")) {
				printHelpAndExit(options);
			}
			return new ParamsBuffer().setInput(cli.getOptionValue("s")).setInteractive(cli.hasOption("i")).setAnsiOld(cli.getOptionValue("ao"))
					.setAnsiNew(cli.getOptionValue("an")).setCharNew(cli.getOptionValue("cn")).setCharOld(cli.getOptionValue("co")).setDiff(cli.hasOption("d")).setColored(cli.hasOption("c"));
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
