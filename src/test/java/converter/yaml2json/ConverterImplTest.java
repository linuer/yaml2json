package converter.yaml2json;

import static org.assertj.core.api.Assertions.*;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.After;
import org.junit.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public class ConverterImplTest {

    public static final Path ACTUAL_PATH = Paths.get("actual.json");
    private ConverterImpl converter;

    public ConverterImplTest() {
        this.converter = new ConverterImpl();
    }

    @Test
    public void should_converted_yaml_be_equal_to_expected_json() throws Exception {
        // given
        Path given = Paths.get(ConverterImplTest.class.getResource("given.yaml").toURI());
        Path expected = Paths.get(ConverterImplTest.class.getResource("expected.json").toURI());
        ImmutableMap<String, Object> expectedJsonMap;
        try (Reader reader = new InputStreamReader(Files.newInputStream(expected, StandardOpenOption.READ))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            Map<String, Object> fromJson = gson.fromJson(reader, type);
            expectedJsonMap = ImmutableMap.copyOf(fromJson);
        }

        // when
        ImmutableMap<String, Object> actualJsonMap = converter.convert(given);

        // then
        assertThat(actualJsonMap).isEqualTo(expectedJsonMap);
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(ACTUAL_PATH);
    }
}
