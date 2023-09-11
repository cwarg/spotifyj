package spotifyj;
import spotifyj.authentication.authentication;
import spotifyj.utilities.utilities;
import spotifyj.userInterface.musicPlayer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class spotifyjTest {

    public static void tester() throws IOException {
        try {
            authentication spotifyAuth = new authentication();
            utilities utilities = new utilities();
            HashMap<String, String> credentials = utilities.loadProperties();

            HashMap<String, String> response = spotifyAuth.requestBearerToken(credentials);
            String bearerToken = response.get("access_token");
            musicPlayer mp = new musicPlayer(bearerToken);
//            mp.play();
            System.out.println(utilities.generateCodeChallenge(utilities.generateRandomString(128)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
