package com.scvh.apps.util.pars;

public class OutputParams {

	private String input;
	private String output;
	private String ansiNew;
	private String ansiOld;
	private boolean diff;
	private boolean colored;

	public String getOutput() {
		return output;
	}

	void setOutput(String output) {
		this.output = output;
	}

	public boolean isDiff() {
		return diff;
	}

	void setDiff(boolean diff) {
		this.diff = diff;
	}

	public boolean isColored() {
		return colored;
	}

	void setColored(boolean colored) {
		this.colored = colored;
	}

	public String getInput() {
		return input;
	}

	void setInput(String input) {
		this.input = input;
	}

	public String getAnsiNew() {
		return ansiNew;
	}

	void setAnsiNew(String ansiNew) {
		this.ansiNew = ansiNew;
	}

	public String getAnsiOld() {
		return ansiOld;
	}

	void setAnsiOld(String ansiOld) {
		this.ansiOld = ansiOld;
	}

}
