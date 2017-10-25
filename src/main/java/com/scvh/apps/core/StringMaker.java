package com.scvh.apps.core;

import com.scvh.apps.util.CLIParser;
import com.scvh.apps.util.InteractiveInputReader;
import com.scvh.apps.util.pars.ParamsBuffer;


/**
 * Just wrapper for getting input
 */
public class StringMaker {

	private CLIParser cliParser;
	private InteractiveInputReader reader;

	public StringMaker(CLIParser cliParser, InteractiveInputReader reader) {
		this.cliParser = cliParser;
		this.reader = reader;
	}

	/**
	 * Retrieve input params from cli
	 */
	public ParamsBuffer retrieveInput(String[] args) {
		return cliParser.parseInput(args);
	}

	/**
	 * Retrieve input params from interactive input
	 */
	public String getStringFromInteractiveStdin() {
		return reader.readInput();
	}
}
