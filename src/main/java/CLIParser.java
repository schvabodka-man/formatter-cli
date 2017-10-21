import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CLIParser {

    private CommandLine cli;

    public CLIParser(CommandLine cli) {
        this.cli = cli;
    }

    private Options getOptions() {
        Option string = Option.builder("s").required().hasArg().longOpt("string").desc("Input string").build();
        return new Options().addOption(string);
    }

    public String parseInput(String[] args) {
        try {
            cli = new DefaultParser().parse(getOptions(), args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cli.getOptionValue("s");
    }
}
