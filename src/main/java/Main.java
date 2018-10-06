import org.apache.log4j.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());

    public static void main(String[] args) {
        LOGGER.info("method main start");
        LOGGER.warn("some warning");
        LOGGER.error("some error");
    }
}
