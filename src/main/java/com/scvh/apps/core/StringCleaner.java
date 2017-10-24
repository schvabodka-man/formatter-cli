package com.scvh.apps.core;

import com.scvh.apps.util.pars.OutputParams;
import com.scvh.apps.util.pars.ParamsBuilder;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringCleaner {

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
				.collect(Collectors.toList()).get(0);
	}

	public OutputParams cleanString(ParamsBuilder builder) {
		return builder.setOutput(Stream.of(builder.getTempInputInstance()).map(this::makeSpacing).map(this::downcaseAll).map(this::upcaseString)
				.map(this::upcaseBeginningOfEachSentence)
				.collect(Collectors.toList()).get(0)).build();
	}

	private String upcaseString(String string) {
		StringBuffer buffer = new StringBuffer();
		Matcher matcher = Pattern.compile("[a-z]+").matcher(string);
		matcher.find();
		matcher.appendReplacement(buffer, StringUtils.capitalize(matcher.group(0)));
		matcher.appendTail(buffer);
		return buffer.toString();
	}

	private String downcaseAll(String string) {
		return StringUtils.lowerCase(string);
	}

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

	private String properDotSpacing(String string) {
		return matchAndReplace(string, "[\\s]*?[\\.?!]+[\\s]*?(?=\\S|$)", true);
	}

	private String upcaseBeginningOfEachSentence(String string) {
		return matchAndReplace(string, "[\\.!?]\\s[^a-z]*?[a-z]", false);
	}

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
