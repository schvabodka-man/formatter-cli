package com.scvh.apps.core;

import com.scvh.apps.util.CLIParser;
import com.scvh.apps.util.pars.ParamsBuffer;

import java.io.IOException;

public class StringMaker {

    private CLIParser cliParser;

    public StringMaker(CLIParser cliParser) {
        this.cliParser = cliParser;
    }

	public ParamsBuffer retrieveInput(String[] args) throws IOException {
		return cliParser.parseInput(args);
    }
}
