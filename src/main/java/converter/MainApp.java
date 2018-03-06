package converter;

import converter.yaml2json.ConvertException;
import converter.yaml2json.Converter;
import converter.yaml2json.ConverterImpl;

public class MainApp {

    public static void main(String[] args) {
        Cli cli = new Cli(args);
        Converter converter = new ConverterImpl();

        try {
            converter.convert(cli.sourceFilePath, cli.targetFilePath);
        } catch (ConvertException e) {
            System.out.println(String.format("Could not convert yaml <%s> to json <%s>. Check help below.", e.inputFile, e.outputFile));
            cli.printHelp();
        }
    }
}
