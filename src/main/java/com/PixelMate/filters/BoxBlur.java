package com.lightricks.filters;
import com.lightricks.utils.Convolution;
import java.awt.image.BufferedImage;//A class holding a 2D image in memory

public class BoxBlur //A class that applies a box blur filter to an image
{
    public static BufferedImage apply(BufferedImage originImage, int width, int height) 
    {
        double[][] kernel = new double[height][width];
        double v = 1.0 / (width * height);//Normalization factor for the kernel
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                kernel[y][x] = v;//Make the kernel values equal to the normalization factor
            }
        }
        return Convolution.convolve(originImage, kernel);//Switching the values of the kernel with the values of the image
    }
}
