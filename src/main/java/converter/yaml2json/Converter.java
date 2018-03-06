package converter.yaml2json;

import java.nio.file.Path;

public interface Converter {
    void convert(Path inputFile, Path outputFile);
}
