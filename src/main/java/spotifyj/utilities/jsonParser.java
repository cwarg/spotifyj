package main.java.spotifyj.utilities;

import java.util.Arrays;
import java.util.HashMap;

public class jsonParser {
    public HashMap<String, String> parseJSON(String input) {

        HashMap<String, String> jsonObject = new HashMap<>();

        String[] splitJSON = input.split(",");
        for (String split : splitJSON) {

            String[] finalSplit = split.replace("{","").replace("}", "").replace("\"", "").strip().split(":");
            jsonObject.put(finalSplit[0], finalSplit[1]);

            }

        return jsonObject;

    }


}
