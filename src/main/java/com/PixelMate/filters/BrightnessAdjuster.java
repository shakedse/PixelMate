package com.pixelmate.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class BrightnessAdjuster {// A class that applies a brightness adjustment filter to an image
    public static BufferedImage adjust(BufferedImage originImage, double factor) {
        int width = originImage.getWidth();
        int height = originImage.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Create a new image with the same dimensions and type as the original
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(originImage.getRGB(x, y), true);
                // Adjust the brightness of the pixel color
                int r = (int) (color.getRed() * factor);
                int g = (int) (color.getGreen() * factor);
                int b = (int) (color.getBlue() * factor);

                r = Math.min(255, Math.max(0, r));
                g = Math.min(255, Math.max(0, g));
                b = Math.min(255, Math.max(0, b));

                // Ensure the color values are within the valid range [0, 255]
                Color newColor = new Color(r, g, b, color.getAlpha());
                result.setRGB(x, y, newColor.getRGB());
            }
        }

        return result;
    }
}
