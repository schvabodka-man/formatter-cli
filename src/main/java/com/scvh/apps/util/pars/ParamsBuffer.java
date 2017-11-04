package com.scvh.apps.util.pars;

/**
 * Buffer/builder for {@link OutputParams}
 */
public class ParamsBuffer {

	private OutputParams params;

	public ParamsBuffer() {
		params = new OutputParams();
	}

	public ParamsBuffer setInput(String input) {
		params.setInput(input);
		return this;
	}

	public ParamsBuffer setOutput(String output) {
		params.setOutput(output);
		return this;
	}

	public ParamsBuffer setAnsiNew(String ansi) {
		params.setAnsiNew(ansi);
		return this;
	}

	public ParamsBuffer setAnsiOld(String ansi) {
		params.setAnsiOld(ansi);
		return this;
	}

	public ParamsBuffer setDiff(boolean diff) {
		params.setDiff(diff);
		return this;
	}

	public ParamsBuffer setColored(boolean colored) {
		params.setColored(colored);
		return this;
	}

	public ParamsBuffer setInteractive(boolean interactive) {
		params.setInteractive(interactive);
		return this;
	}

	public String getTempInputInstance() {
		return params.getInput();
	}

	public OutputParams build() {
		return params;
	}
}
