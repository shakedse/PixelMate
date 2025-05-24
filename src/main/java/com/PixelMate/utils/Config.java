package com.pixelmate.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.List;
import java.util.Map;

public class Config {
    public String input; // Path to the input image file
    public String output; // Path to the output image file
    public boolean display; // Whether to display the output image
    public List<Map<String, Object>> operations; // List of operations to apply (from config)

    /**
     * Constructor to initialize Config from a JSONObject.
     * 
     * @param json The JSONObject parsed from the config file (config.json).
     */
    public Config(JSONObject json) {
        this.input = json.getString("input"); // Read input path
        this.output = json.optString("output", null); // Read output path (optional)
        this.display = json.optBoolean("display", false); // Read display flag (optional)
        this.operations = (List<Map<String, Object>>) (List<?>) json.getJSONArray("operations").toList();
        // Convert JSONArray of operations to List<Map<String,Object>>
    }
}
