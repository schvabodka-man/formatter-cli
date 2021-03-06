package com.scvh.apps.core;

import com.scvh.apps.util.pars.ParamsBuffer;

import org.junit.Assert;
import org.junit.Test;

public class StringCleanerTest {

	private com.scvh.apps.core.StringCleaner cleaner;

	public StringCleanerTest() {
		cleaner = new com.scvh.apps.core.StringCleaner();
	}

	@Test
	public void testFirstString() {
		String test = "with its powerful tools and dazzling effects,Keynote makes it Easy to create stunning and memorable presentations. ";
		String expected = "With its powerful tools and dazzling effects, keynote makes it easy to create stunning and memorable presentations.";
		String result = cleaner.cleanString(new ParamsBuffer().setInput(test)).getOutput();
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testSecondString() {
		String test = "See Who you ’re working with ... While you’re editing, a separate list lets you quickly see who else is in the presentation.";
		String expected = "See who you’re working with... While you’re editing, a separate list lets you quickly see who else is in the presentation.";
		String result = cleaner.cleanString(new ParamsBuffer().setInput(test)).getOutput();
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testQuotes() {
		String test = "« Test » ❝Test ❞ ❛ Test❜";
		String expected = "«Test» ❝test❞ ❛test❜";
		String result = cleaner.cleanString(new ParamsBuffer().setInput(test)).getOutput();
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMultipleSentences() {
		String test = "test123  .  Test 456.test!!test0000.test?????? ";
		String expected = "Test123. Test 456. Test!! Test0000. Test??????";
		String result = cleaner.cleanString(new ParamsBuffer().setInput(test)).getOutput();
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testQuotesOnSentences() {
		String test = "test 123. \"test\" - testing test. ❛ test, test❜. ❝ Test ❞ - test. ";
		String expected = "Test 123. \"Test\" - testing test. ❛Test, test❜. ❝Test❞ - test.";
		String result = cleaner.cleanString(new ParamsBuffer().setInput(test)).getOutput();
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testLoadsOfSpaces() {
		String test = "test 123                 test.    ";
		String expected = "Test 123 test.";
		String result = cleaner.cleanString(new ParamsBuffer().setInput(test)).getOutput();
		Assert.assertEquals(expected, result);
	}
}
