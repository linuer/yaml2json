package converter.yaml2json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.Validate;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Objects;

public class ConverterImpl implements Converter {

    private Yaml yaml = new Yaml();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void convert(Path inputFile, Path outputFile) {
        Objects.requireNonNull(inputFile, "inputFile should NOT be null");
        Validate.isTrue(Files.isReadable(inputFile), "inputFile should be readable");
        Validate.isTrue(!Files.exists(outputFile), "outputFile should not exists");

        try (final InputStream is = Files.newInputStream(inputFile, StandardOpenOption.READ);
             final BufferedWriter br = Files.newBufferedWriter(outputFile, StandardOpenOption.CREATE)) {
            final Map<String, Object> map = (Map<String, Object>) yaml.load(is);
            gson.toJson(map, br);
        } catch (IOException e) {
            throw new ConvertException(e, inputFile, outputFile);
        }
    }
}
