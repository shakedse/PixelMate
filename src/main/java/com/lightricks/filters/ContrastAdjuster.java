package com.lightricks.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ContrastAdjuster {

    // A class that applies a contrast adjustment filter to an image
    public static BufferedImage adjust(BufferedImage originalImage, double factor) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage adjustedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(originalImage.getRGB(x, y), true);

                int red = clamp((int) ((color.getRed() - 128) * factor + 128));
                int green = clamp((int) ((color.getGreen() - 128) * factor + 128));
                int blue = clamp((int) ((color.getBlue() - 128) * factor + 128));

                Color newColor = new Color(red, green, blue, color.getAlpha());
                adjustedImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return adjustedImage;
    }

    // Ensures color values are within [0, 255]
    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }
}
