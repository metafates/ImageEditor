package main.java.transformations;

import main.java.wrappers.Size;

import java.awt.image.BufferedImage;
import java.util.function.Consumer;

/**
 * Crops image to the given size
 */
public class Crop extends Transformation {
    public Crop(Size size) {
        super((Consumer<BufferedImage>) image -> cropTransformation(image, size));
    }

    private static void cropTransformation(final BufferedImage image, final Size size) {
        // TODO: implement crop transformation
    }
}
