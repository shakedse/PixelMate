package com.pixelmate.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class Config {
    public String input;
    public String output;
    public boolean display;
    public List<Map<String, Object>> operations;

    public Config(JSONObject json) {
        this.input = json.getString("input");
        this.output = json.optString("output", null);
        this.display = json.optBoolean("display", false);
        this.operations = (List<Map<String, Object>>) (List<?>) json.getJSONArray("operations").toList();
    }
}
