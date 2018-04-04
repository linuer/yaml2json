package converter;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

final class Cli {

    private static final Logger LOGGER = LoggerFactory.getLogger(Cli.class);
    private static final Options OPTIONS;

    static {
        OPTIONS = new Options();
        OPTIONS.addOption(Option.builder("v").longOpt("version").hasArg(false).desc("version of the application").build());
        OPTIONS.addOption(Option.builder("h").longOpt("help").hasArg(false).desc("shows help options").build());
        OPTIONS.addOption(Option.builder("s").longOpt("source").hasArg(true).desc("path to source file").build());
        OPTIONS.addOption(Option.builder("t").longOpt("target").hasArg(true).desc("path to target file").build());
    }

    Path sourceFilePath;
    Path targetFilePath;

    Cli(String... args) {
        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(OPTIONS, Objects.requireNonNull(args));
            if (cmd.hasOption("v")) {
                printVersion();
            }
            if(cmd.hasOption("h")) {
                printHelp();
            }
            if (cmd.hasOption("s") && cmd.hasOption("t")) {
                sourceFilePath = Paths.get(cmd.getOptionValue("s"));
                targetFilePath = Paths.get(cmd.getOptionValue("t"));
            } else {
                printHelp();
            }
        } catch (ParseException e) {
            LOGGER.warn("Execption during parsing commands", e);
            printHelp();
        }
    }

    void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("yaml2json", OPTIONS);
        System.exit(0);
    }

    private void printVersion() {
        System.out.println("version");
        System.exit(0);
    }
}
