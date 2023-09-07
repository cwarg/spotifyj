package main.java.spotifyj;

import main.java.spotifyj.authentication.authentication;

import java.io.IOException;
import java.util.HashMap;

public class spotifyj {
    public static void main(String[] args) throws IOException, InterruptedException {
        authentication spotifyAuth = new authentication();
        HashMap<String, String> credentials = spotifyAuth.loadProperties();

        String bearer = spotifyAuth.requestBearerToken(credentials);
        System.out.println(bearer);
    }
}
