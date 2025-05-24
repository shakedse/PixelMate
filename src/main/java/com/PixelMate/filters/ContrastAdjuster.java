package com.pixelmate.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ContrastAdjuster {

    /**
     * Applies a contrast adjustment filter to the given image.
     * 
     * @param originalImage The input image to adjust.
     * @param factor        The contrast adjustment factor (greater than 1 to
     *                      increase contrast, between 0 and 1 to decrease).
     * @return A new BufferedImage with the contrast adjusted.
     */
    public static BufferedImage adjust(BufferedImage originalImage, double factor) {
        int width = originalImage.getWidth(); // Get image width
        int height = originalImage.getHeight(); // Get image height

        // Create a new image with the same dimensions and type as the original
        BufferedImage adjustedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Loop through each pixel of the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(originalImage.getRGB(x, y), true); // Get the original color (with alpha)

                // Apply contrast adjustment formula for each channel (R,G,B)
                // Formula: new_value = (old_value - 128) * factor + 128
                int red = clamp((int) ((color.getRed() - 128) * factor + 128));
                int green = clamp((int) ((color.getGreen() - 128) * factor + 128));
                int blue = clamp((int) ((color.getBlue() - 128) * factor + 128));

                // Create a new color with the adjusted RGB values and original alpha
                Color newColor = new Color(red, green, blue, color.getAlpha());
                adjustedImage.setRGB(x, y, newColor.getRGB()); // Set the new pixel value
            }
        }

        return adjustedImage; // Return the contrast-adjusted image
    }

    /**
     * Ensures color values stay within the valid range [0, 255].
     * 
     * @param value The input color value.
     * @return The clamped value.
     */
    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }
}
