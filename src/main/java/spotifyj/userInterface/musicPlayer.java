package spotifyj.userInterface;

import spotifyj.utilities.utilities;

import java.io.IOException;

public class musicPlayer {

    utilities utilities = new utilities();
    String bearerToken;

    public musicPlayer(String m_bearerToken) {
        bearerToken = m_bearerToken;
    }
    public void search() throws IOException, InterruptedException {

        System.out.println("Enter a search string:");
        String userSearchString = utilities.getUserInput();
        String formattedInputString = utilities.inputParser(userSearchString);
        String q;
        System.out.println("Please choose what you would like to search for:");
        System.out.println("""
                +--------+-----------+
                | Choice | Function  |
                +--------+-----------+
                |      1 | Album     |
                |      2 | Artist    |
                |      3 | Playlist  |
                |      4 | Track     |
                |      5 | Show      |
                |      6 | Episode   |
                |      7 | Audiobook |
                +--------+-----------+
                """);
        String userIn = utilities.getUserInput();

        switch (userIn) {
            case "1":
                q = utilities.searchUriCreator(formattedInputString, "album");
                System.out.println(utilities.querySpotifyAPI(bearerToken, q));
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                break;
            case "7":
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }





    }
    public void pause() {}
    public void skip() {}
    public void toggleShuffle() {}
    public void setVolume() {}
    public void toggleRepeat() {}
}
