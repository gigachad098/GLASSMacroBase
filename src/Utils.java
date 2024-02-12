import java.util.logging.Level;
import java.util.logging.Logger;
public class Utils {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static void logString(String logString) {
        LOGGER.log(Level.INFO, logString);
    }
}
