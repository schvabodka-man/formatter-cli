package core;

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

    public String cleanString(String string) {
        return Stream.of(string).map(this::makeSpacing).map(this::downcaseAll).map(this::upcaseString)
                .map(this::upcaseBeginningOfEachSentence)
                .collect(Collectors.toList()).get(0);
    }

    private String upcaseString(String string) {
        return StringUtils.capitalize(string);
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
        Matcher matcher = Pattern.compile("[\\s]*?\\.+[\\s]*?(?=\\S)").matcher(string);
        String cleaned = string;
        while (matcher.find()) {
            cleaned = StringUtils.replace(cleaned, matcher.group(0), matcher.group(0).replaceAll(" +", "") + " ");
        }
        return cleaned;
    }

    private String upcaseBeginningOfEachSentence(String string) {
        Matcher matcher = Pattern.compile("[\\.!?]\\s[a-z]").matcher(string);
        String cleaned = string;
        while (matcher.find()) {
            cleaned = StringUtils.replace(cleaned, matcher.group(0), matcher.group(0).toUpperCase()); //a little bit of immutability goes here. Instead of StringBuilder
        }
        return cleaned;
    }
}
