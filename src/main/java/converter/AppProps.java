package converter;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

class AppProps {

    private static final URL DEFAULT_PROPERTIES_URL = AppProps.class.getClassLoader().getResource("application.properties");

    private static final String PROP_VERSION = "version";
    private static final String PROP_APP_NAME = "appName";

    public final String version;
    public final String appName;

    AppProps() {
        URL url = getClass().getClassLoader().getResource("application.properties");
        try (InputStream is = url.openStream()) {
            Properties props = new Properties();
            props.load(is);
            version = props.getProperty(PROP_VERSION);
            appName = props.getProperty(PROP_APP_NAME);
        } catch (Exception e) {
            throw new LoadAppPropsException(e);
        }
    }

    private static final class LoadAppPropsException extends RuntimeException {
        private LoadAppPropsException(Exception e) {
            super(e);
        }
    }
}
