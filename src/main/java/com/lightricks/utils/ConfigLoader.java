package com.lightricks.utils;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigLoader {

    // This method loads a config file from disk and turns it into a Config object
    public static Config load(String filePath) throws Exception {
        // Read the whole file content as a string
        String fileContent = Files.readString(Path.of(filePath));

        // Parse the string into a JSON object
        JSONObject jsonObject = new JSONObject(fileContent);

        // Create and return a Config object from the JSON
        return new Config(jsonObject);
    }
}
