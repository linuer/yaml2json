package converter.jsonsaver;

import com.google.common.collect.ImmutableMap;
import org.immutables.value.Value;

import java.nio.file.Path;

@Value.Immutable
@Value.Style(init = "with*")
public interface JsonSaveParam {
    ImmutableMap<String, Object> jsonMap();
    Path outputPath();
}
