package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;

public class LoggingUtils {

    public static void logApiError(Logger logger, String responseBody) {
//        logger.error("API Error: {}", responseBody);

        JsonObject errorObject = JsonParser.parseString(responseBody).getAsJsonObject();

        if (errorObject.has("message")) {
            String message = errorObject.get("message").getAsString();
            logger.error("Error message: {}", message);
        }

        if (errorObject.has("errors")) {
            var errorsElement = errorObject.get("errors");

            if (errorsElement.isJsonArray()) {
                JsonArray errors = errorsElement.getAsJsonArray();
                if (errors.size() > 0 && errors.get(0).isJsonObject()) {
                    JsonObject firstError = errors.get(0).getAsJsonObject();
                    String detailedMessage = firstError.has("message") ? firstError.get("message").getAsString() : "N/A";
                    logger.error("Reason: {}", detailedMessage);
                }
            } else if (errorsElement.isJsonObject()) {
                JsonObject error = errorsElement.getAsJsonObject();
                String detailedMessage = error.has("message") ? error.get("message").getAsString() : "N/A";
                logger.error("Reason: {}", detailedMessage);
            } else {
                logger.error("Unknown 'errors' format: {}", errorsElement);
            }
        }

    }
}
