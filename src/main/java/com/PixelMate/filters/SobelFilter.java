package com.pixelmate.filters;

import com.pixelmate.utils.Convolution;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class SobelFilter {

    public static BufferedImage apply(BufferedImage image) {
        double[][] sobelX = {
            { -1, 0, 1 },
            { -2, 0, 2 },
            { -1, 0, 1 }
        };

        double[][] sobelY = {
            { -1, -2, -1 },
            {  0,  0,  0 },
            {  1,  2,  1 }
        };

        BufferedImage imgX = Convolution.convolve(image, sobelX);
        BufferedImage imgY = Convolution.convolve(image, sobelY);

        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color cx = new Color(imgX.getRGB(x, y));
                Color cy = new Color(imgY.getRGB(x, y));
                int alpha = new Color(image.getRGB(x, y), true).getAlpha();

                int r = clamp((int)Math.sqrt(cx.getRed() * cx.getRed() + cy.getRed() * cy.getRed()));
                int g = clamp((int)Math.sqrt(cx.getGreen() * cx.getGreen() + cy.getGreen() * cy.getGreen()));
                int b = clamp((int)Math.sqrt(cx.getBlue() * cx.getBlue() + cy.getBlue() * cy.getBlue()));

                Color edge = new Color(r, g, b, alpha);
                result.setRGB(x, y, edge.getRGB());
            }
        }

        return result;
    }

    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }
}
