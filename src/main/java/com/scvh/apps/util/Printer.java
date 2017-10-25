package com.scvh.apps.util;

import com.github.difflib.algorithm.DiffException;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import com.scvh.apps.util.pars.OutputParams;

import java.util.Collections;
import java.util.List;

/**
 * Prints result to stdout
 */
public class Printer {

	/**
	 * Nice ANSI codes and chars i'm using by default
	 */
	private static final String ANSI_BOLD = "\u001B[1m";
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String DEFAULT_OLD_CHAR = "⨉⨉";
	private static final String DEFAULT_NEW_CHAR = "➕➕";

	/**
	 * Print the given output
	 * @param params the params with output string
	 */
	public void print(OutputParams params) {
		if (params.isDiff()) { //if this is diff print diff
			printDiff(params);
		} else { //if not just print output string
			printGenericOutput(params.getOutput());
		}
	}

	/**
	 * Print one string to stdout
	 */
	private void printGenericOutput(String stdout) {
		System.out.println(stdout);
	}

	/**
	 * Printing one nice formatted diff
	 */
	private void printDiff(OutputParams params) {
		//generate diff params
		DiffRowGenerator generator = DiffRowGenerator.create()
				.showInlineDiffs(true)
				.mergeOriginalRevised(true)
				.inlineDiffByWord(true)
				.oldTag(f -> generateDiffSymbol(params, true))
				.newTag(f -> generateDiffSymbol(params, false)).build();
		//make diff string
		List<DiffRow> rows = null;
		try {
			rows = generator.generateDiffRows(
					Collections.singletonList(params.getInput()),
					Collections.singletonList(params.getOutput()));
		} catch (DiffException e) {
			e.printStackTrace();
			System.exit(1); //exit after exception
		}
		//print it
		rows.forEach(row -> System.out.println(row.getOldLine()));
	}

	/**
	 * Simple wrapper for neat generation of diff row
	 */
	private String generateDiffSymbol(OutputParams params, boolean oldOrNew) {
		return getProperAnsiCodeFormat(params, oldOrNew) + getProperCharacterForDiff(params, oldOrNew) + ANSI_RESET;
	}

	//two pretty similar methods. Pretty much they would look nice with macro

	/**
	 * This one is here for returning generic ansi codes for removed and added stuff in diff
	 * Default is {@link Printer#ANSI_RED} and {@link Printer#ANSI_GREEN}
	 */
	private String getProperAnsiCodeFormat(OutputParams params, boolean oldOrNew) {
		//first check if there is user specified codes and if there are return them
		if (oldOrNew && params.getAnsiOld() != null) {
			return params.getAnsiOld();
		} else if (!oldOrNew && params.getAnsiNew() != null) {
			return params.getAnsiNew();
		}
		//if not and input is not set to colored return ANSI_BOLD
		else if (!params.isColored()) {
			return ANSI_BOLD;
		} else {
			//if colored return needed code
			if (!oldOrNew) {
				return ANSI_GREEN;
			} else {
				return ANSI_RED;
			}
		}
	}

	/**
	 * This one is here for returning generic symbols for removed and added stuff in diff
	 * Default is {@link Printer#DEFAULT_OLD_CHAR} and {@link Printer#DEFAULT_NEW_CHAR}
	 */
	private String getProperCharacterForDiff(OutputParams params, boolean oldOrNew) {
		//the logics is pretty much the same as upper one
		if (oldOrNew && params.getCharOld() != null) {
			return params.getCharOld();
		} else if (!oldOrNew && params.getCharNew() != null) {
			return params.getCharNew();
		} else {
			if (!oldOrNew) {
				return DEFAULT_NEW_CHAR;
			} else {
				return DEFAULT_OLD_CHAR;
			}
		}
	}
}
