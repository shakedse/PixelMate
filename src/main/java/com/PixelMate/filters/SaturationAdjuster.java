package com.pixelmate.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SaturationAdjuster {

    /**
     * Adjusts the saturation of the given image by a specified factor.
     * 
     * @param image  The original BufferedImage to adjust.
     * @param factor The saturation adjustment factor (1 = no change, >1 = more
     *               saturated, <1 = desaturated).
     * @return A new BufferedImage with adjusted saturation.
     */
    public static BufferedImage adjust(BufferedImage image, double factor) {
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
                result.setRGB(x, y, adjusted.getRGB()); // Set adjusted pixel
            }
        }

        return result;
    }

    /**
     * Ensures color values stay within [0, 255].
     * 
     * @param value The input color value.
     * @return The clamped value.
     */
    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }
}
