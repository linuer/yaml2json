package converter.yaml2json;

import com.google.common.collect.ImmutableMap;

import java.io.IOException;
import java.nio.file.Path;

public interface Converter {

    ImmutableMap<String, Object> convert(Path path) throws IOException;

    ImmutableMap<String, Object> convert(InputStreamWithClosedProperty is);
}
