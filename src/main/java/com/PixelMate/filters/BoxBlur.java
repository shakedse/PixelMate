package com.pixelmate.filters;

import com.pixelmate.utils.Convolution;
import java.awt.image.BufferedImage;
import java.util.Map;

public class BoxBlur implements Filter {

    // Applies a box blur filter to the input image with specified kernel size.
    // Generates a kernel (matrix) where all values are equal and sums to 1.
    public static BufferedImage apply(BufferedImage image, int width, int height) {
        double[][] kernel = new double[height][width];
        double value = 1.0 / (width * height); // Each cell has equal weight

        // Fill the kernel with the calculated value
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                kernel[y][x] = value;
            }
        }

        // Apply convolution using the generated kernel
        return Convolution.convolve(image, kernel);
    }

    // Implements the Filter interface apply method.
    // Extracts 'width' and 'height' from parameters, then calls the static apply
    // method.
    @Override
    public BufferedImage apply(BufferedImage image, Map<String, Object> parameters) {
        int width = ((Number) parameters.get("width")).intValue();
        int height = ((Number) parameters.get("height")).intValue();
        return apply(image, width, height);
    }
}
