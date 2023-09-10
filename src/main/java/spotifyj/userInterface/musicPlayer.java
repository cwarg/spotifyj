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
                +--------+-----------+
                """);
        String userIn = utilities.getUserInput();

        switch (userIn) {
            case "1":
                q = utilities.searchUriCreator(formattedInputString, "album");
                System.out.println(utilities.getSpotifyAPI(bearerToken, q, ""));
                //TODO: once the albums are returned, possibly play the whole album or look at tracks specifically in that album.
                //sout("1. Play album 2. List Tracks")
                //Within another ascii table, might as well keep that format for now
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }





    }
    public void play() throws IOException, InterruptedException {
        utilities.putSpotifyAPI(bearerToken, "https://api.spotify.com/v1/me/player/play", "{\n" +
                "    \"context_uri\": \"spotify:album:5ht7ItJgpBH7W6vJ5BqpPr\",\n" +
                "    \"offset\": {\n" +
                "        \"position\": 5\n" +
                "    },\n" +
                "    \"position_ms\": 0\n" +
                "}");
    }
    public void pause() {}
    public void skip() {}
    public void toggleShuffle() {}
    public void setVolume() {}
    public void toggleRepeat() {}
}
