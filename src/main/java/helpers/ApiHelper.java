package helpers;

import com.google.gson.Gson;
import controllers.RfqResponse;
import org.apache.commons.lang3.tuple.Pair;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ApiHelper {
    public static String baseURI;
    public static String token;
    public static Gson gson = new Gson();

    public static void setup() {
        try {
            var props = new java.util.Properties();
            props.load(Files.newInputStream(Paths.get("src/test/resources/config.properties")));
            baseURI = props.getProperty("baseURI");
            token = props.getProperty("Token");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static Pair<Integer, RfqResponse> createPostText(String jsonFile) {
        return sendPostRequest("/api/rfq/upload-free-text", readJson(jsonFile));
    }

    public static Pair<Integer, RfqResponse> createPostUrl(String jsonFile) {
        return sendPostRequest("/api/rfq/upload-url-html", readJson(jsonFile));
    }

    public static Pair<Integer, RfqResponse> sendPostRequest(String endpoint, String jsonBody) {
        String url = baseURI + endpoint;
        HttpResponse<JsonNode> response = Unirest.post(url)
                .header("Content-Type", "application/json")
                .header("Authorization", "ApiKey " + token)
                .body(jsonBody)
                .asJson();

        RfqResponse rfqResponse = gson.fromJson(response.getBody().toString(), RfqResponse.class);
        return Pair.of(response.getStatus(), rfqResponse);
    }

    private static String readJson(String filePath) {
        try {
            return Files.readString(Paths.get("src/test/resources/" + filePath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + filePath, e);
        }
    }
}