package main.java.spotifyj.authentication;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Properties;
import java.net.http.*;
import java.util.Map;
import java.util.stream.Collectors;
import main.java.spotifyj.utilities.jsonParser;

public class authentication {

    public HashMap<String, String> loadProperties() {
        try {
            FileInputStream fis = new FileInputStream("spotifyj.properties");
            Properties props = new Properties();
            props.load(fis);

            HashMap<String, String> finalProps = new HashMap<>();

            finalProps.put("spotifyj_clientid", props.getProperty("spotifyj.client_id"));
            finalProps.put("spotifyj_clientsecret", props.getProperty("spotifyj.client_secret"));

            return finalProps;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpRequest.BodyPublisher encodeURLParams(Map<String, String> parameters) {
        String urlEncoded = parameters.entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
        return HttpRequest.BodyPublishers.ofString(urlEncoded);
    }

    public HashMap<String, String> requestBearerToken(HashMap<String, String> credentials) throws IOException, InterruptedException {

        jsonParser jsonparser = new jsonParser();

        String tokenURI = "https://accounts.spotify.com/api/token";
        Map<String, String> bodyParams = new HashMap<>();
        bodyParams.put("grant_type", "client_credentials");

        String authorizationHeader = "Basic " + Base64.getEncoder().encodeToString((credentials.get("spotifyj_clientid") + ":" + credentials.get("spotifyj_clientsecret")).getBytes());

        HttpClient httpclient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(tokenURI))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", authorizationHeader)
                .POST(encodeURLParams(bodyParams))
                .build();
        HttpResponse<String> response = httpclient.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        int statusCode = response.statusCode();
        return jsonparser.parseJSON(body);
    }

}
