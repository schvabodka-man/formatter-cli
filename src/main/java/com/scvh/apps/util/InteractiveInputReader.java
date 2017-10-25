package com.scvh.apps.util;

import java.util.Scanner;


/**
 * Reader for user input in interactive mode
 */
public class InteractiveInputReader {

	private Scanner scanner;

	/**
	 * @param scanner the scanner
	 */
	public InteractiveInputReader(Scanner scanner) {
		this.scanner = scanner;
	}

	/**
	 * Just get a string from stdin
	 * @return string from user input
	 */
	public String readInput() {
		return scanner.nextLine();
	}
}
