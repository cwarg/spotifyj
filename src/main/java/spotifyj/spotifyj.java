package main.java.spotifyj;

import main.java.spotifyj.authentication.authentication;
import main.java.spotifyj.utilities.utilities;

import java.io.IOException;
import java.util.HashMap;

public class spotifyj {
    public static void main(String[] args) throws IOException, InterruptedException {
        authentication spotifyAuth = new authentication();
        utilities utilities = new utilities();
        HashMap<String, String> credentials = utilities.loadProperties();

        HashMap<String, String> response = spotifyAuth.requestBearerToken(credentials);
        String bearerToken = response.get("access_token");
        String responseCode = response.get("status_code");
    }
}
