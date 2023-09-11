package spotifyj.authentication;

import java.io.IOException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.net.http.*;

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

    public void requestUserAuth() throws IOException, InterruptedException, NoSuchAlgorithmException {
        String tokenURI = "https://accounts.spotify.com/authorize?";
        String state = utilities.generateRandomString(16);
        String redirectURL = "http://localhost:9081";
        String codeVerifier = utilities.generateRandomString(128);
        String codeChallenge = utilities.generateCodeChallenge(codeVerifier);

        Map<String, String> args = new HashMap<>();

        args.put("response_type", "code");
        args.put("client_id", this.credentials.get("spotifyj_clientid"));
        args.put("redirect_uri", redirectURL);
        args.put("state", state);
        args.put("code_challenge_method", "S256");
        args.put("code_challenge", codeChallenge);



        System.out.println(tokenURI + utilities.encodeURLParamsString(args));
    }

}
