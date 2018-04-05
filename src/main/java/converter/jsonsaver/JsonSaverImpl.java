package converter.jsonsaver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class JsonSaverImpl implements JsonSaver {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Path save(JsonSaveParam jsonSaverParam) {
        Validate.notNull(jsonSaverParam, "json output should not be null");
        Path outputFile = jsonSaverParam.outputPath();
        try (final Writer bw = Files.newBufferedWriter(outputFile, StandardOpenOption.CREATE)) {
            gson.toJson(jsonSaverParam.jsonMap(), bw);
        } catch (IOException e) {
            throw new JsonSaveException(e, outputFile);
        }

        return outputFile;
    }
}
