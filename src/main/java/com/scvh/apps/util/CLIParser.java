package com.scvh.apps.util;

import com.scvh.apps.util.pars.ParamsBuffer;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.List;

/**
 * Wrapper around apache commons cli. Is here for parsing cli input
 */
public class CLIParser {

	/**
	 * Simply returns object with all cli args
	 */
	private Options getOptions() {
		Option help = Option.builder("h").longOpt("help").desc("Help").build();
		Option diff = Option.builder("d").longOpt("diff").desc("Show diff").build();
		Option colors = Option.builder("c").longOpt("color").desc("Colorize output").build();
		Option interactive = Option.builder("i").longOpt("interactive").desc("Run app interactively").build();
		Option ansiNew = Option.builder("an").hasArg().longOpt("ansi-new").desc("Ansi char for new stuff in diff").build();
		Option ansiOld = Option.builder("ao").hasArg().longOpt("ansi-old").desc("Ansi char for old stuff in diff").build();
		return new Options().addOption(help).addOption(diff).addOption(colors)
				.addOption(ansiNew).addOption(ansiOld).addOption(interactive);
	}

	/**
	 * Parse input from cli
	 * @param args the args from cli
	 * @return Prepared ParamsBuffer with input params
	 */
	public ParamsBuffer parseInput(String[] args) {
		try {
			Options options = getOptions();
			CommandLine cli = new DefaultParser().parse(options, args);
			if (cli.hasOption("h")) { //if there is "help options" print help and finish program
				printHelpAndExit(options);
			}
			return new ParamsBuffer().setInput(retrieveMainString(options, cli)).setInteractive(cli.hasOption("i")).setAnsiOld(cli.getOptionValue("ao"))
					.setAnsiNew(cli.getOptionValue("an")).setDiff(cli.hasOption("d")).setColored(cli.hasOption("c"));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Simply prints list with all possible args as help
	 */
	private void printHelpAndExit(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("formatter-cli", options);
		System.exit(0);
	}

	/**
	 * @param options     cli options list to simply throw help with list of options
	 * @param commandLine cli object from apache commons to parse stuff
	 * @return string from cli args
	 */
	private String retrieveMainString(Options options, CommandLine commandLine) {
		List<String> args = commandLine.getArgList();
		if (args.size() == 0) {
			printHelpAndExit(options);
			return null;
		} else {
			return args.get(0);
		}
	}

}
