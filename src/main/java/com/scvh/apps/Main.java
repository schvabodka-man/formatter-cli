package com.scvh.apps;

import com.github.difflib.algorithm.DiffException;
import com.scvh.apps.core.StringCleaner;
import com.scvh.apps.core.StringMaker;
import com.scvh.apps.util.CLIParser;
import com.scvh.apps.util.InteractiveInputReader;
import com.scvh.apps.util.Printer;
import com.scvh.apps.util.pars.ParamsBuffer;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException, DiffException {
		StringMaker maker = new StringMaker(new CLIParser());
		StringCleaner cleaner = new StringCleaner();
		Printer priner = new Printer();
		ParamsBuffer buffer = maker.retrieveInput(args);
		if (!buffer.build().isInteractive()) {
			print(priner, cleaner, buffer);
		} else {
			InteractiveInputReader reader = new InteractiveInputReader(new Scanner(System.in));
			print(priner, cleaner, buffer);
			while (true) {
				print(priner, cleaner, buffer.setInput(reader.readInput()));
			}
		}
	}

	private static void print(Printer printer, StringCleaner cleaner, ParamsBuffer buffer) {
		printer.print(cleaner.cleanString(buffer));
	}
}
