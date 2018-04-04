package converter;

import com.google.common.collect.ImmutableMap;
import converter.jsonsaver.*;
import converter.yaml2json.Converter;
import converter.yaml2json.ConverterImpl;

import java.io.IOException;

public class MainApp {

    public static void main(String[] args) {
        Cli cli = new Cli(args);

        Converter converter = new ConverterImpl();
        JsonSaver saver = new JsonSaverImpl();

        try {
            ImmutableMap<String, Object> jsonMap = converter.convert(cli.sourceFilePath);
            JsonSaveParam jsonSaveParam = ImmutableJsonSaveParam.builder()
                    .withJsonMap(jsonMap)
                    .withOutputPath(cli.targetFilePath)
                    .build();

            saver.save(jsonSaveParam);

        } catch (IOException e) {
            throw new RuntimeException("reading exception", e);
        } catch (JsonSaveException e) {
            System.out.println(String.format("Could not save to fallowing path <%s>. Check help below.", e.outputFile));
            cli.printHelp();
        }
    }
}
