package com.scvh.apps.util;

import java.util.Scanner;

public class InteractiveInputReader {

	private Scanner scanner;

	public InteractiveInputReader(Scanner scanner) {
		this.scanner = scanner;
	}

	public String readInput() {
		return scanner.nextLine();
	}
}
