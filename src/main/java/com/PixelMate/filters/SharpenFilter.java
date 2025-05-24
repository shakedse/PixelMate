package com.lightricks.filters;

import com.lightricks.utils.Convolution;
import java.awt.image.BufferedImage;

public class SharpenFilter {

    // This matrix (kernel) sharpens the image by emphasizing edges
    private static final double[][] sharpenMatrix = {
        { 0.0, -1.0,  0.0 },
        { -1.0,  5.0, -1.0 },
        { 0.0, -1.0,  0.0 }
    };

    // Applies the sharpen filter using the convolution utility
    public static BufferedImage apply(BufferedImage originalImage) {
        return Convolution.convolve(originalImage, sharpenMatrix);
    }
}
