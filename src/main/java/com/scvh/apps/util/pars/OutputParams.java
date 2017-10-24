package com.scvh.apps.util.pars;

public class OutputParams {

	private String input;
	private String output;
	private boolean diff;
	private boolean colored;
	private boolean help;

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

	public boolean isHelp() {
		return help;
	}
}
