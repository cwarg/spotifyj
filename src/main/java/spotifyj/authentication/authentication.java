package spotifyj.authentication;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;
import java.util.HashMap;
import java.net.http.*;
import java.util.Map;
import spotifyj.utilities.utilities;

public class authentication {

    utilities utilities = new utilities();
    HashMap<String, String> credentials;

    public authentication() {
        this.credentials = utilities.loadProperties();
    }

    public HashMap<String, String> requestBearerToken() throws IOException, InterruptedException {

        String tokenURI = "https://accounts.spotify.com/api/token";
        Map<String, String> bodyParams = new HashMap<>();
        bodyParams.put("grant_type", "client_credentials");

        String authorizationHeader = "Basic " + Base64.getEncoder().encodeToString((this.credentials.get("spotifyj_clientid") + ":" + credentials.get("spotifyj_clientsecret")).getBytes());

        HttpClient httpclient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(tokenURI))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", authorizationHeader)
                .POST(utilities.encodeURLParams(bodyParams))
                .build();
        HttpResponse<String> response = httpclient.send(request, HttpResponse.BodyHandlers.ofString());
        String statusCode = String.valueOf(response.statusCode());
        HashMap<String, String> responseObject = utilities.parseJSON(response.body());
        responseObject.put("status_code", statusCode);
        return responseObject;
    }

    public void requestUserAuth() throws IOException, InterruptedException {
        String tokenURI = "https://accounts.spotify.com/authorize?";
        String state = utilities.generateRandomString(16);
    }

}
