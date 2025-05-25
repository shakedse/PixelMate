package com.pixelmate.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Map;

public class BrightnessAdjuster implements Filter {

    // Applies a brightness adjustment filter to the given image.
    // Multiplies each color channel (R, G, B) by the factor.
    public static BufferedImage apply(BufferedImage originImage, double factor) {
        int width = originImage.getWidth();
        int height = originImage.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Loop over each pixel and adjust brightness
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(originImage.getRGB(x, y), true);
                int r = (int) (color.getRed() * factor);
                int g = (int) (color.getGreen() * factor);
                int b = (int) (color.getBlue() * factor);

                // Clamp the values to [0, 255]
                r = Math.min(255, Math.max(0, r));
                g = Math.min(255, Math.max(0, g));
                b = Math.min(255, Math.max(0, b));

                Color newColor = new Color(r, g, b, color.getAlpha());
                result.setRGB(x, y, newColor.getRGB());
            }
        }

        return result;
    }

    // Implements the Filter interface apply method.
    // Extracts the 'value' parameter for brightness and calls the static apply
    // method.
    @Override
    public BufferedImage apply(BufferedImage image, Map<String, Object> parameters) {
        double factor = ((Number) parameters.get("value")).doubleValue();
        return apply(image, factor);
    }
}
