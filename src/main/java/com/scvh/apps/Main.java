package com.scvh.apps;

import com.scvh.apps.core.StringCleaner;
import com.scvh.apps.core.StringMaker;
import com.scvh.apps.util.CLIParser;
import com.scvh.apps.util.InteractiveInputReader;
import com.scvh.apps.util.Printer;
import com.scvh.apps.util.pars.ParamsBuffer;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		StringMaker maker = new StringMaker(new CLIParser(), new InteractiveInputReader(new Scanner(System.in)));
		StringCleaner cleaner = new StringCleaner();
		Printer priner = new Printer();
		ParamsBuffer buffer = maker.retrieveInput(args);
		if (!buffer.build().isInteractive()) {
			print(priner, cleaner, buffer);
		} else {
			print(priner, cleaner, buffer);
			while (true) {
				print(priner, cleaner, buffer.setInput(maker.getStringFromInteractiveStdin()));
			}
		}
	}

	private static void print(Printer printer, StringCleaner cleaner, ParamsBuffer buffer) {
		printer.print(cleaner.cleanString(buffer));
	}
}
