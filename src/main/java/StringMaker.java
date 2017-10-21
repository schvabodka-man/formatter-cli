import org.apache.commons.lang3.StringUtils;

public class StringMaker {

    private CLIParser parser;

    public StringMaker(CLIParser parser) {
        this.parser = parser;
    }

    private String upcaseString(String string) {
        return StringUtils.capitalize(string);
    }

    public String retrieveInput(String[] args) {
        return upcaseString(parser.parseInput(args));
    }
}
