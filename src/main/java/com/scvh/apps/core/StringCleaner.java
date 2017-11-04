package com.scvh.apps.core;

import com.scvh.apps.util.pars.OutputParams;
import com.scvh.apps.util.pars.ParamsBuffer;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The core logic class itself that cleans string
 */
public class StringCleaner {


	/**
	 * Main driver method itself
	 */
	public OutputParams cleanString(ParamsBuffer builder) {
		//First cleaning spacing, than making proper casing
		return builder.setOutput(Stream.of(builder.getTempInputInstance()).map(this::makeSpacing).map(this::downcaseAll).map(this::upcaseString)
				.map(this::upcaseBeginningOfEachSentence)
				.collect(Collectors.toList()).get(0)).build();
	}

	/**
	 * Making a neat spacing and other stuff like dots and commas
	 * @param string temp string
	 * @return string with proper spacing
	 */
	private String makeSpacing(String string) {
		//Java doesn't have monads out of the box so i'm making my own sort of monad
		return Stream.of(string).map(val -> StringUtils.replaceAll(val, "[\\s]*?,[\\s]*?(?=\\S)", ", "))
				.map(this::properDotSpacing)
				.map(this::niceQuoting)
				.map(dots -> StringUtils.replaceAll(dots, "[\\s]*?:[\\s]*?(?=\\S)", ": "))
				.map(locs -> StringUtils.replaceAll(locs, "[\\s]*?;+[\\s]*?(?=\\S)", ";\n"))
				.map(semilocs -> StringUtils.replaceAll(semilocs, "[\\.+] $", "."))
				.map(lastdot -> StringUtils.replaceAll(lastdot, "[?] $", "?"))
				.map(lastquestion -> StringUtils.replaceAll(lastquestion, "[!] $", "!"))
				.map(last -> StringUtils.replaceAll(last, "[\\s+]*?’[\\s+]*?", "’"))
				.map(last -> StringUtils.replaceAll(last, " +", " "))
				.collect(Collectors.toList()).get(0);
	}

	/**
	 * Smartly upcasing first letter of string
	 */
	private String upcaseString(String string) {
		StringBuffer buffer = new StringBuffer();
		Matcher matcher = Pattern.compile("[a-z]+").matcher(string);
		matcher.find();
		matcher.appendReplacement(buffer, StringUtils.capitalize(matcher.group(0)));
		matcher.appendTail(buffer);
		return buffer.toString();
	}

	/**
	 * Not smartly downcasing everything
	 */
	private String downcaseAll(String string) {
		return StringUtils.lowerCase(string);
	}

	/**
	 * Making proper spacing with quote marks
	 */
	private String niceQuoting(String string) {
		return Stream.of(string).map(toReplace -> StringUtils.replaceAll(toReplace, "\\(\\s+", "("))
				.map(toReplace -> StringUtils.replaceAll(toReplace, "\\s+\\)", ")"))
				.map(toReplace -> StringUtils.replaceAll(toReplace, "«\\s+", "«"))
				.map(toReplace -> StringUtils.replaceAll(toReplace, "\\s+»", "»"))
				.map(toReplace -> StringUtils.replaceAll(toReplace, "❝\\s+", "❝"))
				.map(toReplace -> StringUtils.replaceAll(toReplace, "\\s+❞", "❞"))
				.map(toReplace -> StringUtils.replaceAll(toReplace, "❛\\s+", "❛"))
				.map(toReplace -> StringUtils.replaceAll(toReplace, "\\s+❜", "❜"))
				.collect(Collectors.toList()).get(0);
	}

	/**
	 * Making proper spacing in end of sentence
	 */
	private String properDotSpacing(String string) {
		return matchAndReplace(string, "[\\s]*?[\\.?!]+[\\s]*?(?=\\S|$)", true);
	}

	/**
	 * Upcasing beginning of each sentence
	 */
	private String upcaseBeginningOfEachSentence(String string) {
		return matchAndReplace(string, "[\\.!?]\\s[^a-z]*?[a-z]", false);
	}

	/**
	 * Generic method for matching by regexp and replacing
	 */
	private String matchAndReplace(String string, String regex, boolean dotOrUpcase) {
		StringBuffer buffer = new StringBuffer();
		Matcher matcher = Pattern.compile(regex).matcher(string);
		while (matcher.find()) {
			if (dotOrUpcase) {
				matcher.appendReplacement(buffer, matcher.group(0).replaceAll(" +", "") + " ");
			} else {
				matcher.appendReplacement(buffer, matcher.group(0).toUpperCase());
			}
		}
		matcher.appendTail(buffer);
		return buffer.toString();
	}
}
