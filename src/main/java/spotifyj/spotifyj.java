package spotifyj;

import spotifyj.authentication.authentication;
import spotifyj.utilities.utilities;
import spotifyj.userInterface.musicPlayer;
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

        musicPlayer mp = new musicPlayer(bearerToken);

        while (true) {

            System.out.println("\nWhat would you like to do?");
            System.out.println("""
                    +--------+----------+
                    | Choice | Function |
                    +--------+----------+
                    |      1 | Search   |
                    |      2 | Pause    |
                    |      3 | Test     |
                    +--------+----------+
                    """);

            String userInput = utilities.getUserInput();

            switch (userInput) {
                case "1":
                    mp.search();
                    break;
                case "2":
                    System.out.println("you chose 2");
                    break;
                case "3":
                    System.out.println("you chose 3");
                    break;
                default:
                    System.out.println("you chose incorrectly");
                    break;

            }
        }
    }
}
