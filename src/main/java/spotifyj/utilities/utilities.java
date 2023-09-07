package main.java.spotifyj.utilities;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

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
}
