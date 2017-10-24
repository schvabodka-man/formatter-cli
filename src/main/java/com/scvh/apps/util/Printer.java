package com.scvh.apps.util;

import com.github.difflib.algorithm.DiffException;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import com.scvh.apps.util.pars.OutputParams;

import java.util.Collections;
import java.util.List;

public class Printer {

	private static final String ANSI_BOLD = "\\033[1m";
	private static final String ANSI_RESET = "\\033[0m";
	private static final String ANSI_RED = "\\e[0;31m";
	private static final String ANSI_GREEN = "\\e[0;31m";

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
				.oldTag(f -> ANSI_RED + "✕✕" + ANSI_RESET)
				.newTag(f -> ANSI_GREEN + "++" + ANSI_RESET).build();
		List<DiffRow> rows = generator.generateDiffRows(
				Collections.singletonList(params.getInput()),
				Collections.singletonList(params.getOutput()));
		rows.forEach(row -> System.out.println(row.getOldLine()));
	}

}
