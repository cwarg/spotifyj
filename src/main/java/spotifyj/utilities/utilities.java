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

    public String getSpotifyAPI(String bearerToken, String stringURI, String body) throws IOException, InterruptedException {
        String authorizationHeader = "Bearer " + bearerToken;

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(stringURI))
                .header("Authorization", authorizationHeader)
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String statusCode = String.valueOf(response.statusCode());
        return response.body();
    }

    public String postSpotifyAPI (String bearerToken, String stringURI, String body) throws IOException, InterruptedException {
        String authorizationHeader = "Bearer " + bearerToken;

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(stringURI))
                .header("Authorization", authorizationHeader)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String statusCode = String.valueOf(response.statusCode());
        return response.body();

    }

    public String putSpotifyAPI (String bearerToken, String stringURI, String body) throws IOException, InterruptedException {
        String authorizationHeader = "Bearer " + bearerToken;

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(stringURI))
                .header("Authorization", authorizationHeader)
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String statusCode = String.valueOf(response.statusCode());
        System.out.println(response.body());
        System.out.println(statusCode);
        return response.body();
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

    public void animateText(FileInputStream inputFileToPrint) throws IOException, InterruptedException {
        int character;
        List<Integer> fileContent = new ArrayList<Integer>();
        while ((character = inputFileToPrint.read()) != -1) {
            fileContent.add(character);
        }

        for (int printableChar : fileContent) {
            System.out.print((char)printableChar);
            Thread.sleep(1);
        }
    }
}
