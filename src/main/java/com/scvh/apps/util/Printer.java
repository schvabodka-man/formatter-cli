package com.scvh.apps.util;

import com.github.difflib.algorithm.DiffException;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import com.scvh.apps.util.pars.OutputParams;

import java.util.Collections;
import java.util.List;

public class Printer {

	private static final String ANSI_BOLD = "\u001B[1m";
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String DEFAULT_OLD_CHAR = "⨉⨉";
	private static final String DEFAULT_NEW_CHAR = "➕➕";

	public void print(OutputParams params) {
		if (params.isDiff()) {
			try {
				printDiff(params);
			} catch (DiffException e) {
				e.printStackTrace();
			}
		} else {
			printGenericOutput(params.getOutput());
		}
	}

	private void printGenericOutput(String stdout) {
		System.out.println(stdout);
	}

	private void printDiff(OutputParams params) throws com.github.difflib.algorithm.DiffException {
		DiffRowGenerator generator = DiffRowGenerator.create()
				.showInlineDiffs(true)
				.mergeOriginalRevised(true)
				.inlineDiffByWord(true)
				.oldTag(f -> generateDiffSymbol(params, true))
				.newTag(f -> generateDiffSymbol(params, false)).build();
		List<DiffRow> rows = generator.generateDiffRows(
				Collections.singletonList(params.getInput()),
				Collections.singletonList(params.getOutput()));
		rows.forEach(row -> System.out.println(row.getOldLine()));
	}

	private String generateDiffSymbol(OutputParams params, boolean oldOrNew) {
		return getProperAnsiCodeFormat(params, oldOrNew) + getProperCharacterForDiff(params, oldOrNew) + ANSI_RESET;
	}

	//TODO Replace on macro
	private String getProperAnsiCodeFormat(OutputParams params, boolean oldOrNew) {
		if (oldOrNew && params.getAnsiOld() != null) {
			return params.getAnsiOld();
		} else if (!oldOrNew && params.getAnsiNew() != null) {
			return params.getAnsiNew();
		} else if (!params.isColored()) {
			return ANSI_BOLD;
		} else {
			if (!oldOrNew) {
				return ANSI_GREEN;
			} else {
				return ANSI_RED;
			}
		}
	}

	//TODO Replace on macro
	private String getProperCharacterForDiff(OutputParams params, boolean oldOrNew) {
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
