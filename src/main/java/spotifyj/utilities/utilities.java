package spotifyj.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class utilities {
    public HashMap<String, String> loadProperties() {
        try {
            FileInputStream propertiesInput = new FileInputStream("spotifyj.properties");
            Properties props = new Properties();
            props.load(propertiesInput);

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

    public HashMap<String, String> parseJSON(String input) {

        HashMap<String, String> jsonObject = new HashMap<>();

        String[] commaSplit = input.split(",");
        for (String split : commaSplit) {

            String[] colonSplit = split.replace("{","").replace("}", "").replace("\"", "").strip().split(":");
            jsonObject.put(colonSplit[0], colonSplit[1]);

        }
        return jsonObject;
    }

    public String getUserInput() {
        Scanner userIn = new Scanner(System.in);
        String userText = userIn.nextLine();
        return userText;
    }

    public HttpRequest.BodyPublisher encodeURLParams(Map<String, String> parameters) {
        String urlEncoded = parameters.entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
        return HttpRequest.BodyPublishers.ofString(urlEncoded);
    }

    public String querySpotifyAPI(String bearerToken, String stringURI, String httpMethod) throws IOException, InterruptedException {
        String authorizationHeader = "Bearer " + bearerToken;

        HttpClient httpclient = HttpClient.newHttpClient();
        if (httpMethod == "GET") {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(stringURI))
                    .header("Authorization", authorizationHeader)
                    .GET()
                    .build();
            HttpResponse<String> response = httpclient.send(request, HttpResponse.BodyHandlers.ofString());
            String statusCode = String.valueOf(response.statusCode());
            return response.body();
        }
        else if (httpMethod == "POST") {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(stringURI))
                    .header("Authorization", authorizationHeader)
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = httpclient.send(request, HttpResponse.BodyHandlers.ofString());
            String statusCode = String.valueOf(response.statusCode());
            return response.body();
        }
        else if (httpMethod == "PUT") {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(stringURI))
                    .header("Authorization", authorizationHeader)
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = httpclient.send(request, HttpResponse.BodyHandlers.ofString());
            String statusCode = String.valueOf(response.statusCode());
            return response.body();
        }
        return null;
    }

    public String inputParser(String inputString) {
        return inputString.replace(" ", "+");
    }

    public String searchUriCreator (String userInputString, String userSearchCategory) {
        return "https://api.spotify.com/v1/search?" + "q=" + userInputString + "&type=" + userSearchCategory;
    }

    public void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void animateText() {
        //TODO: load file and print characters one by one to imitate animating
    }
}
