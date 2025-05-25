package com.pixelmate.filters;

import java.awt.image.BufferedImage;
import java.util.Map;

public interface Filter {
    BufferedImage apply(BufferedImage image, Map<String, Object> parameters);
}
// This interface defines a contract for image filters in the PixelMate
// application.
// Each filter must implement the apply method, which takes an image and a map
// of parameters,