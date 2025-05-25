package com.pixelmate.filters;

import com.pixelmate.utils.Convolution;
import java.awt.image.BufferedImage;
import java.util.Map;

public class SharpenFilter implements Filter {

    // Matrix (kernel) used to sharpen the image by emphasizing edges
    private static final double[][] sharpenMatrix = {
            { 0.0, -1.0, 0.0 },
            { -1.0, 5.0, -1.0 },
            { 0.0, -1.0, 0.0 }
    };

    // Applies the sharpen filter to the image using the convolution method.
    public static BufferedImage apply(BufferedImage originalImage) {
        return Convolution.convolve(originalImage, sharpenMatrix);
    }

    // Implements the Filter interface apply method.
    // Calls the static apply method since this filter does not require parameters.
    @Override
    public BufferedImage apply(BufferedImage image, Map<String, Object> parameters) {
        return apply(image);
    }
}
