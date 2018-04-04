package converter.yaml2json;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.Validate;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public class ConverterImpl implements Converter {

    private Yaml yaml = new Yaml();

    @Override
    public ImmutableMap<String, Object> convert(Path inputFile) throws IOException {
        try (InputStreamWithClosedProperty is =
                     new InputStreamWithClosedProperty(Files.newInputStream(inputFile, StandardOpenOption.READ))) {
            return convert(is);
        }
    }

    @Override
    public ImmutableMap<String, Object> convert(InputStreamWithClosedProperty inputStream) {
        Validate.notNull(inputStream, "input stream should not be null");
        Validate.isTrue(!inputStream.isClosed(), "input stream should not be closed");

        return ImmutableMap.copyOf((Map<String, Object>) yaml.load(inputStream));
    }
}
