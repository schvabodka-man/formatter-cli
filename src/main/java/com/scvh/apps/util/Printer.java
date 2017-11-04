package com.scvh.apps.util;

import com.github.difflib.algorithm.DiffException;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import com.scvh.apps.util.pars.OutputParams;

import org.fusesource.jansi.Ansi;

import java.util.Collections;
import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * Prints result to stdout
 */
public class Printer {

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
				.showInlineDiffs(false)
				.mergeOriginalRevised(false)
				.inlineDiffByWord(false)
				.build();
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
		rows.forEach(row -> System.out.println(generateDiffLine(params, row.getNewLine(), row.getOldLine())));
	}

	/**
	 * Simple wrapper for neat generation of diff row
	 */
	private Ansi generateDiffLine(OutputParams params, String newLine, String oldLine) {
		Ansi buffer = ansi();
		if (params.getAnsiOld() != null) {
			buffer.a(params.getAnsiOld()).a(oldLine).reset().newline();
		} else if (params.getAnsiNew() != null) {
			buffer.a(params.getAnsiNew()).a(newLine).reset().newline();
		} else if (!params.isColored()) {
			buffer.a(oldLine).reset().newline();
			buffer.bold().a(newLine).boldOff().reset().newline();
		} else {
			buffer.fgBrightRed().a(oldLine).reset().newline();
			buffer.fgBrightGreen().a(newLine).reset().newline();
		}
		return buffer;
	}

}
