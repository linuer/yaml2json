package converter.yaml2json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class MainApp {

    public static void main(String[] args) {
        Yaml yaml = new Yaml();
        try (InputStream is = MainApp.class.getClassLoader().getResourceAsStream("example1.yaml")) {
            Map<String, Object> map = (Map<String, Object>)yaml.load(is);
            System.out.println(map);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(map));
        } catch (IOException ioEx) {
            throw new RuntimeException(ioEx);
        }
    }
}
