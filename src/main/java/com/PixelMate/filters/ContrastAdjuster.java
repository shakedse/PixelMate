package com.pixelmate.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Map;

public class ContrastAdjuster implements Filter {

    // Applies a contrast adjustment filter to the given image.
    // The 'factor' parameter controls the contrast level.
    public static BufferedImage apply(BufferedImage originalImage, double factor) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        BufferedImage adjustedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Loop through each pixel and adjust contrast
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(originalImage.getRGB(x, y), true);

                // Adjust each color channel using the contrast formula
                int red = clamp((int) ((color.getRed() - 128) * factor + 128));
                int green = clamp((int) ((color.getGreen() - 128) * factor + 128));
                int blue = clamp((int) ((color.getBlue() - 128) * factor + 128));

                // Create new color with adjusted RGB values and original alpha
                Color newColor = new Color(red, green, blue, color.getAlpha());
                adjustedImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return adjustedImage;
    }

    // Implements the Filter interface apply method.
    // Extracts 'value' from parameters and applies the contrast adjustment.
    @Override
    public BufferedImage apply(BufferedImage image, Map<String, Object> parameters) {
        double factor = ((Number) parameters.get("value")).doubleValue();
        return apply(image, factor);
    }

    // Ensures color values stay within the valid range [0, 255].
    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }
}
