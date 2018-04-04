package converter.yaml2json;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

final class InputStreamWithClosedProperty extends InputStream {
    private boolean closed = false;
    private final InputStream inputStream;

    InputStreamWithClosedProperty(InputStream inputStream) {
        this.inputStream = Objects.requireNonNull(inputStream);
    }

    @Override
    public int read() throws IOException {
        return inputStream.read();
    }

    @Override
    public void close() throws IOException {
        super.close();
        closed = true;
    }

    boolean isClosed() {
        return closed;
    }
}
