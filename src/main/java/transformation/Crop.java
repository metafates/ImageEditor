package main.java.transformation;

import main.java.wrappers.Size;

import java.awt.image.BufferedImage;

/**
 * Crops image to the given size
 */
public class Crop extends Transformation {
    public Crop(Size size) {
        super(image -> cropTransformation(image, size));
    }

    private static void cropTransformation(final BufferedImage image, final Size size) {
        // TODO: implement crop transformation
    }
}
