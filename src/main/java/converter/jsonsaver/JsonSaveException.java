package converter.jsonsaver;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.nio.file.Path;

public class JsonSaveException extends RuntimeException {

    public final Path outputFile;

    public JsonSaveException(Throwable cause, Path outputFile) {
        super(cause);
        this.outputFile = outputFile;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
