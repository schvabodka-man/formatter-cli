package com.scvh.apps.util;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class Printer {

    public void printGenericOutput(String stdout) {
        System.out.println(stdout);
    }

    void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("formatter-cli", options);
        System.exit(0);
    }
}
