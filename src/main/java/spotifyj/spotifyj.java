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
        boolean finished = false;

        musicPlayer mp = new musicPlayer(bearerToken);

        while (!finished) {
            utilities.clearTerminal();
            System.out.println("""
                                 ,-.----.                ,--.'|_     ,--,      .--.,                        \s
                                 \\    /  \\     ,---.     |  | :,'  ,--.'|    ,--.'  \\                   .--.\s
                      .--.--.    |   :    |   '   ,'\\    :  : ' :  |  |,     |  | /\\/                 .--,`|\s
                     /  /    '   |   | .\\ :  /   /   | .;__,'  /   `--'_     :  : :         .--,      |  |. \s
                    |  :  /`./   .   : |: | .   ; ,. : |  |   |    ,' ,'|    :  | |-,     /_ ./|      '--`_ \s
                    |  :  ;_     |   |  \\ : '   | |: : :__,'| :    '  | |    |  : :/|  , ' , ' :      ,--,'|\s
                     \\  \\    `.  |   : .  | '   | .; :   '  : |__  |  | :    |  |  .' /___/ \\: |      |  | '\s
                      `----.   \\ :     |`-' |   :    |   |  | '.'| '  : |__  '  : '    .  \\  ' |      :  | |\s
                     /  /`--'  / :   : :     \\   \\  /    ;  :    ; |  | '.'| |  | |     \\  ;   :    __|  : '\s
                    '--'.     /  |   | :      `----'     |  ,   /  ;  :    ; |  : \\      \\  \\  ;  .'__/\\_: |\s
                      `--'---'   `---'.|                  ---`-'   |  ,   /  |  |,'       :  \\  \\ |   :    :\s
                                   `---`                            ---`-'   `--'          \\  ' ;  \\   \\  / \s
                                                                                            `--`    `--`-'  \s""");

            System.out.println("\nWhat would you like to do?");
            System.out.println("""
                    +--------+----------+------------------------------------------------------
                    | Choice | Function |
                    +--------+----------+
                    |      1 | Search   |
                    |      2 | Pause    |
                    |      3 | Test     |
                    |      4 | Quit     |
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
                case "4":
                    finished = true;
                    break;
                default:
                    System.out.println("you chose incorrectly");
                    break;

            }
        }
    }
}
