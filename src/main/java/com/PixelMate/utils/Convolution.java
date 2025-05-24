package com.pixelmate.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Convolution {

    /**
     * Applies a convolution operation to the input image using the specified
     * kernel.
     * 
     * @param image  The input BufferedImage to process.
     * @param kernel The convolution kernel (2D matrix of weights).
     * @return A new BufferedImage with the convolution applied.
     */
    public static BufferedImage convolve(BufferedImage image, double[][] kernel) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        int kernelHeight = kernel.length;
        int kernelWidth = kernel[0].length;

        // Calculate the center of the kernel matrix
        int kernelCenterY = kernelHeight / 2;
        int kernelCenterX = kernelWidth / 2;

        // Prepare the output image with the same dimensions as the input
        BufferedImage outputImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);

        // Process each pixel of the image
        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {

                double redSum = 0;
                double greenSum = 0;
                double blueSum = 0;

                // Apply the kernel over the neighboring pixels
                for (int ky = 0; ky < kernelHeight; ky++) {
                    for (int kx = 0; kx < kernelWidth; kx++) {

                        int imageX = x + (kx - kernelCenterX);
                        int imageY = y + (ky - kernelCenterY);

                        // Check if neighbor pixel is within bounds
                        if (imageX >= 0 && imageX < imageWidth && imageY >= 0 && imageY < imageHeight) {
                            Color neighborColor = new Color(image.getRGB(imageX, imageY));
                            double weight = kernel[ky][kx];

                            redSum += neighborColor.getRed() * weight;
                            greenSum += neighborColor.getGreen() * weight;
                            blueSum += neighborColor.getBlue() * weight;
                        }
                    }
                }

                // Clamp color values to [0,255]
                int finalRed = clamp((int) Math.round(redSum));
                int finalGreen = clamp((int) Math.round(greenSum));
                int finalBlue = clamp((int) Math.round(blueSum));

                int alpha = new Color(image.getRGB(x, y), true).getAlpha(); // Preserve original alpha

                Color finalColor = new Color(finalRed, finalGreen, finalBlue, alpha);
                outputImage.setRGB(x, y, finalColor.getRGB());
            }
        }

        return outputImage;
    }

    /**
     * Ensures color values stay within [0, 255].
     */
    private static int clamp(int value) {
        if (value < 0)
            return 0;
        if (value > 255)
            return 255;
        return value;
    }
}
