package com.scvh.apps.core;

import com.scvh.apps.util.CLIParser;
import com.scvh.apps.util.pars.ParamsBuilder;

import java.io.IOException;

public class StringMaker {

    private CLIParser cliParser;

    public StringMaker(CLIParser cliParser) {
        this.cliParser = cliParser;
    }

    public ParamsBuilder retrieveInput(String[] args) throws IOException {
        return cliParser.parseInput(args);
    }
}
