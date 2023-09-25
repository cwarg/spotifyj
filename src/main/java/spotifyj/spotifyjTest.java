package spotifyj;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import spotifyj.authentication.authentication;
import spotifyj.utilities.utilities;
import spotifyj.userInterface.musicPlayer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.net.*;

public class spotifyjTest {

    public static void tester() throws IOException {
        try {
            authentication spotifyAuth = new authentication();
            utilities utilities = new utilities();
//            HashMap<String, String> response = spotifyAuth.requestBearerToken();
//            String bearerToken = response.get("access_token");
//            musicPlayer mp = new musicPlayer(bearerToken);
//            mp.play();
            HttpServer server = utilities.HttpServerOpen();
            spotifyAuth.requestUserAuth();
            utilities.HttpServerClose(server);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
