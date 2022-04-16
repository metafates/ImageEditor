package main.java.transformation;

import java.awt.image.BufferedImage;
import java.util.function.Consumer;

/**
 * Base transformation class that applies given consumer function to the image
 */
public class Transformation implements Consumer<BufferedImage> {
    private final Consumer<BufferedImage> transformation;

    public Transformation(final Consumer<BufferedImage> transformation) {
        this.transformation = transformation;
    }

    @Override
    public void accept(BufferedImage bufferedImage) {
        transformation.accept(bufferedImage);
    }
}
