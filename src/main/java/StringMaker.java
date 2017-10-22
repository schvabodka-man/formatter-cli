import utils.CLIParser;

public class StringMaker {

    private CLIParser parser;

    public StringMaker(CLIParser parser) {
        this.parser = parser;
    }

    public String retrieveInput(String[] args) {
        return parser.parseInput(args);
    }
}
