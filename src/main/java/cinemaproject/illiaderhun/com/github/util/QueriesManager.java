package cinemaproject.illiaderhun.com.github.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class QueriesManager {

    private static final Logger LOGGER = Logger.getLogger(QueriesManager.class.getSimpleName());

    public static Properties getProperties(String forWhom) {
        LOGGER.info("method getProperties start with parameter: " + forWhom);

        String pathToProperties = "queries/" + forWhom + "/queries.properties";
        LOGGER.info("generated path: " + pathToProperties);
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        Properties properties = new Properties();

        try(InputStream resourceStream = loader.getResourceAsStream(pathToProperties)) {
            properties.load(resourceStream);
            LOGGER.info("properties has been loaded");
        } catch (IOException e) {
            LOGGER.error("method getProperties caught IOException");
            LOGGER.trace(e);
        }

        LOGGER.info("method getProperties returns: " + properties);
        return properties;
    }
}
