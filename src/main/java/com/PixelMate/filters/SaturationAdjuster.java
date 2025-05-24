package com.pixelmate.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SaturationAdjuster {

    public static BufferedImage adjust(BufferedImage image, double factor) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Create a new image with the same dimensions and type as the original
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color original = new Color(image.getRGB(x, y), true);

                int r = original.getRed();
                int g = original.getGreen();
                int b = original.getBlue();
                int a = original.getAlpha();

                int gray = (r + g + b) / 3;

                int newR = clamp((int)(gray + (r - gray) * factor));
                int newG = clamp((int)(gray + (g - gray) * factor));
                int newB = clamp((int)(gray + (b - gray) * factor));

                Color adjusted = new Color(newR, newG, newB, a);
                result.setRGB(x, y, adjusted.getRGB());
            }
        }

        return result;
    }

    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }
}
