package com.pixelmate.utils;

import com.pixelmate.filters.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class OperationRegistry {

    // Map that links filter names (like "brightness") to their filter implementations
    private static final Map<String, Filter> filtersMap = new HashMap<>();

    // Static block to register all filters when the class is loaded
    static {
        registerAllFilters();
    }

    /**
     * Registers each filter by linking its name to a Filter implementation.
     * This approach uses objects instead of lambda functions for simplicity and flexibility.
     */
    public static void registerAllFilters() {
        filtersMap.put("brightness", new BrightnessAdjuster());
        filtersMap.put("contrast", new ContrastAdjuster());
        filtersMap.put("saturation", new SaturationAdjuster());
        filtersMap.put("box_blur", new BoxBlur());
        filtersMap.put("sharpen", new SharpenFilter());
    }

    /**
     * Applies the specified filter to the image with the given parameters.
     * @param filterName The name of the filter (e.g., "brightness", "contrast").
     * @param image The original BufferedImage to process.
     * @param parameters A map of parameters needed by the filter.
     * @return The processed BufferedImage.
     * @throws IllegalArgumentException if the filter name does not exist.
     */
    public static BufferedImage apply(String filterName, BufferedImage image, Map<String, Object> parameters) {
        Filter filter = filtersMap.get(filterName);

        if (filter == null) {
            throw new IllegalArgumentException("No such filter: " + filterName);
        }

        return filter.apply(image, parameters);
    }
}
