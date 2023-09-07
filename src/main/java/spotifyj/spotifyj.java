package main.java.spotifyj;

import main.java.spotifyj.authentication.authentication;
import main.java.spotifyj.utilities.jsonParser;

import java.io.IOException;
import java.util.HashMap;

public class spotifyj {
    public static void main(String[] args) throws IOException, InterruptedException {
        authentication spotifyAuth = new authentication();
        HashMap<String, String> credentials = spotifyAuth.loadProperties();

        HashMap<String, String> response = spotifyAuth.requestBearerToken(credentials);
        System.out.println(response.get("access_token"));

    }
}
