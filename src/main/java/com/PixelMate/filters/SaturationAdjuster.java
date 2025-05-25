package com.pixelmate.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Map;

public class SaturationAdjuster implements Filter {

    // Adjusts the saturation of the given image by a specified factor.
    public static BufferedImage apply(BufferedImage image, double factor) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Process each pixel in the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color original = new Color(image.getRGB(x, y), true); // Get original color with alpha

                int r = original.getRed();
                int g = original.getGreen();
                int b = original.getBlue();
                int a = original.getAlpha();

                // Calculate the grayscale equivalent of the pixel
                int gray = (r + g + b) / 3;

                // Adjust each color channel towards/away from gray based on factor
                int newR = clamp((int) (gray + (r - gray) * factor));
                int newG = clamp((int) (gray + (g - gray) * factor));
                int newB = clamp((int) (gray + (b - gray) * factor));

                // Create new color with adjusted saturation and original alpha
                Color adjusted = new Color(newR, newG, newB, a);
                result.setRGB(x, y, adjusted.getRGB());
            }
        }

        return result;
    }

    // Implements the Filter interface apply method.
    // Extracts 'value' from parameters and applies the saturation adjustment.
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
