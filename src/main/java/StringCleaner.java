import org.apache.commons.lang3.StringUtils;

public class StringCleaner {

    public String makeSpacing(String string) {
        String commasReplaced = StringUtils.replaceAll(string, ",(?=\\S)", ", ");
        String dotsReplaced = StringUtils.replaceAll(commasReplaced, "\\.(?=\\S)", ". ");
        String locsReplaced = StringUtils.replaceAll(dotsReplaced, ":(?=\\S)", ": ");
        String semiLoconsReplaced = StringUtils.replaceAll(locsReplaced, ";(?=\\S)", ";\n");
        String lastSymbolReplace = StringUtils.replaceAll(semiLoconsReplaced, "[\\.?!] $", ".");
        return lastSymbolReplace;
    }

    public String makeCase(String spaced) {
        return null;
    }
}
