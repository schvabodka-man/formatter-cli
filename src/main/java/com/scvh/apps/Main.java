package com.scvh.apps;

import com.github.difflib.algorithm.DiffException;
import com.scvh.apps.core.StringCleaner;
import com.scvh.apps.core.StringMaker;
import com.scvh.apps.util.CLIParser;
import com.scvh.apps.util.Printer;
import com.scvh.apps.util.pars.ParamsBuilder;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, DiffException {
		StringMaker maker = new StringMaker(new CLIParser());
		StringCleaner cleaner = new StringCleaner();
		ParamsBuilder builder = maker.retrieveInput(args);
		new Printer().print(cleaner.cleanString(builder));
	}
}
