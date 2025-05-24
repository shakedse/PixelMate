package com.pixelmate.utils;

import com.pixelmate.filters.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class OperationRegistry {

    // Map that links operation names (like "brightness") to their filter functions
    private static final Map<String, BiFunction<BufferedImage, Map<String, Object>, BufferedImage>> filtersMap = new HashMap<>();

    // This code runs once when the class loads and registers all filters
    static {
        registerAllFilters();
    }

    // This method connects each operation name to the actual function that handles
    // it
    public static void registerAllFilters() {
        filtersMap.put("brightness", (image, parameters) -> BrightnessAdjuster.adjust(
                image,
                ((Number) parameters.get("value")).doubleValue()));

        filtersMap.put("contrast", (image, parameters) -> ContrastAdjuster.adjust(
                image,
                ((Number) parameters.get("value")).doubleValue()));

        filtersMap.put("saturation", (image, parameters) -> SaturationAdjuster.adjust(
                image,
                ((Number) parameters.get("value")).doubleValue()));

        // Corrected BoxBlur to call the proper apply method
        filtersMap.put("box_blur", (image, parameters) -> BoxBlur.apply(
                image,
                ((Number) parameters.get("width")).intValue(),
                ((Number) parameters.get("height")).intValue()));

        filtersMap.put("sharpen", (image, parameters) -> SharpenFilter.apply(image));

        // Optional: remove or implement UnsharpMaskFilter if needed
        // filtersMap.put("unsharp_mask", (image, parameters) ->
        // UnsharpMaskFilter.apply(image));
    }

    // This method finds the filter by name and applies it with the given parameters
    public static BufferedImage apply(String filterName, BufferedImage image, Map<String, Object> parameters) {
        BiFunction<BufferedImage, Map<String, Object>, BufferedImage> filterFunction = filtersMap.get(filterName);

        if (filterFunction == null) {
            throw new IllegalArgumentException("No such filter: " + filterName);
        }

        return filterFunction.apply(image, parameters);
    }
}
