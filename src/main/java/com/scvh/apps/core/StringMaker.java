package com.scvh.apps.core;

import com.scvh.apps.util.CLIParser;
import com.scvh.apps.util.StdinPipeParser;

import java.io.IOException;

public class StringMaker {

    private CLIParser cliParser;
    private StdinPipeParser stdinPipeParser;

    public StringMaker(CLIParser cliParser, StdinPipeParser stdinPipeParser) {
        this.cliParser = cliParser;
        this.stdinPipeParser = stdinPipeParser;
    }

    public String retrieveInput(String[] args) throws IOException {
        String cli = cliParser.parseInput(args);
        String stdin = stdinPipeParser.getStdin();
        if (cli == null && stdin == null) {
            throw new IOException("No input provided. Please view -h (or --help) for help");
        } else if (cli != null && stdin != null) {
            throw new IOException("Only one input source(stdin or argument), please!");
        } else if (cli != null && stdin == null) {
            return cli;
        } else if (cli == null && stdin != null) {
            return stdin;
        }
        return null;
    }
}
