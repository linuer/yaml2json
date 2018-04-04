package converter.jsonsaver;

import java.nio.file.Path;

public interface JsonSaver {
    Path save(JsonSaveParam output);
}
