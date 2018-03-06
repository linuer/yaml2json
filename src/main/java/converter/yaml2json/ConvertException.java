package converter.yaml2json;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.nio.file.Path;

public class ConvertException extends RuntimeException {

    public final Path inputFile;
    public final Path outputFile;

    public ConvertException(Throwable cause, Path inputFile, Path outputFile) {
        super(cause);
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
