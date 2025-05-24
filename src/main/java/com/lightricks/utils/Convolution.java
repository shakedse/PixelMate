package com.lightricks.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Convolution {

    public static BufferedImage convolve(BufferedImage image, double[][] kernel) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        int kernelHeight = kernel.length;
        int kernelWidth = kernel[0].length;

        int kernelCenterY = kernelHeight / 2;
        int kernelCenterX = kernelWidth / 2;

        BufferedImage outputImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {

                double redSum = 0;
                double greenSum = 0;
                double blueSum = 0;

                for (int ky = 0; ky < kernelHeight; ky++) {
                    for (int kx = 0; kx < kernelWidth; kx++) {

                        int imageX = x + (kx - kernelCenterX);
                        int imageY = y + (ky - kernelCenterY);

                        if (imageX >= 0 && imageX < imageWidth && imageY >= 0 && imageY < imageHeight) {
                            Color neighborColor = new Color(image.getRGB(imageX, imageY));

                            double weight = kernel[ky][kx];

                            redSum += neighborColor.getRed() * weight;
                            greenSum += neighborColor.getGreen() * weight;
                            blueSum += neighborColor.getBlue() * weight;
                        }
                    }
                }

                int finalRed = clamp((int) Math.round(redSum));
                int finalGreen = clamp((int) Math.round(greenSum));
                int finalBlue = clamp((int) Math.round(blueSum));
                int alpha = new Color(image.getRGB(x, y), true).getAlpha();

                Color finalColor = new Color(finalRed, finalGreen, finalBlue, alpha);
                outputImage.setRGB(x, y, finalColor.getRGB());
            }
        }

        return outputImage;
    }

    private static int clamp(int value) {
        if (value < 0)
            return 0;
        if (value > 255)
            return 255;
        return value;
    }
}
