package com.lightricks.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageIOUtil {

    // Load an image from a file path
    public static BufferedImage load(String path) throws Exception {
        return ImageIO.read(new File(path));
    }

    // Save an image to a file (PNG format)
    public static void save(BufferedImage image, String path) throws Exception {
        if (image == null || path == null) 
        {
            throw new IllegalArgumentException("Image or path is null");
        }

        boolean success = ImageIO.write(image, "png", new File(path));

        if (!success) {
            throw new RuntimeException("Failed to save image to: " + path);
        }
    }
}
