package co.com.simulator;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UtilityClass
public class LoggerSimulator {

    public static String TRANSACTION_ID = "";

    public void logInfo(String message) {
        getLogger(getClazz()).info(buildMessage(TRANSACTION_ID, message));
    }

    public void logError(String message) {
        getLogger(getClazz()).info(buildMessage(TRANSACTION_ID, message));
    }

    private Logger getLogger(String clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    private String getClazz() {
        return Thread.currentThread().getStackTrace()[3].getClassName();
    }

    private String buildMessage(String id, String message) {
        return String.format("TRANSACTION_ID: %s, %s", id, message);
    }
}
