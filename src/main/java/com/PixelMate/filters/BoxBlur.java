package com.pixelmate.filters;

import com.pixelmate.utils.Convolution; // Import the convolution utility
import java.awt.image.BufferedImage; // Import BufferedImage for handling images

public class BoxBlur {

    /**
     * Applies a box blur filter to the input image.
     * 
     * @param image  The input image to be processed.
     * @param width  The width of the blur kernel (e.g., 3 for a 3x3 kernel).
     * @param height The height of the blur kernel (e.g., 3 for a 3x3 kernel).
     * @return A new BufferedImage with the box blur applied.
     */
    public static BufferedImage apply(BufferedImage image, int width, int height) {
        // Create a 2D kernel (matrix) of the specified size
        double[][] kernel = new double[height][width];

        // Calculate the value for each cell: 1 / (width * height)
        double value = 1.0 / (width * height);

        // Fill the kernel with the uniform value
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                kernel[y][x] = value; // Each pixel in the kernel has equal weight
            }
        }
        // Perform convolution on the image using the generated kernel
        return Convolution.convolve(image, kernel);
    }
}
