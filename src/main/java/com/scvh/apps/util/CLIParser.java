package com.scvh.apps.util;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CLIParser {

    private Printer printer;

    public CLIParser(Printer printer) {
        this.printer = printer;
    }

    private Options getOptions() {
        Option string = Option.builder("s").hasArg().longOpt("string").desc("Input string").build();
        Option help = Option.builder("h").longOpt("help").desc("Help").build();
        return new Options().addOption(string).addOption(help);
    }

    public String parseInput(String[] args) {
        try {
            CommandLine cli = new DefaultParser().parse(getOptions(), args);
            if (cli.hasOption("h")) {
                printer.printHelp(getOptions());
            }
            return cli.getOptionValue("s");
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
