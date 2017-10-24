package com.scvh.apps.util.pars;

public class ParamsBuilder {

	private OutputParams params;

	public ParamsBuilder() {
		params = new OutputParams();
	}

	public ParamsBuilder setInput(String input) {
		params.setInput(input);
		return this;
	}

	public ParamsBuilder setOutput(String output) {
		params.setOutput(output);
		return this;
	}

	public ParamsBuilder setDiff(boolean diff) {
		params.setDiff(diff);
		return this;
	}

	public ParamsBuilder setColored(boolean colored) {
		params.setColored(colored);
		return this;
	}

	public String getTempInputInstance() {
		return params.getInput();
	}

	public OutputParams build() {
		return params;
	}
}
