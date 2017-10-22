import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringCleaner {

    private String makeSpacing(String string) {
        //Java doesn't have monads out of the box so i'm making my own sort of monad
        return Stream.of(string).map(val -> StringUtils.replaceAll(val, "\\s*?,(?=\\S)", ", "))
                .map(commas -> StringUtils.replaceAll(commas, "\\s*?\\.(?=\\S)(?=[^.])", ". "))
                .map(dots -> StringUtils.replaceAll(dots, "\\s*?:(?=\\S)", ": "))
                .map(locs -> StringUtils.replaceAll(locs, "\\s*?;(?=\\S)", ";\n"))
                .map(semilocs -> StringUtils.replaceAll(semilocs, "[\\.+] $", "."))
                .map(lastdot -> StringUtils.replaceAll(lastdot, "[?] $", "?"))
                .map(lastquestion -> StringUtils.replaceAll(lastquestion, "[!] $", "!"))
                .map(last -> StringUtils.replaceAll(last, "[\\s+]*?’[\\s+]*?", "’"))
                .collect(Collectors.toList()).get(0);
    }

    public String cleanString(String string) {
        return Stream.of(string).map(this::makeSpacing).map(this::downcaseAll).map(this::upcaseString)
                //.map(this::upcaseBeginningOfEachSentence)
                .collect(Collectors.toList()).get(0);
    }

    private String upcaseString(String string) {
        return StringUtils.capitalize(string);
    }

    private String downcaseAll(String string) {
        return StringUtils.lowerCase(string);
    }

    private String upcaseBeginningOfEachSentence(String string) {
        return StringUtils.replaceAll(string, "[\\.!?]\\s[a-z]", "$1".toUpperCase());
    }
}
