package transformations;

import java.awt.image.BufferedImage;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Base transformation class that applies given strategy to the image.
 */
public class Transformation implements Function<BufferedImage, BufferedImage> {
    private final Function<BufferedImage, BufferedImage> strategy;

    public Transformation(final Function<BufferedImage, BufferedImage> transformation) {
        this.strategy = transformation;
    }

    public Transformation(final Consumer<BufferedImage> transformation) {
        // Wrap it into function interface
        this.strategy = (image) -> {
            transformation.accept(image);
            return image;
        };
    }

    @Override
    public BufferedImage apply(BufferedImage bufferedImage) {
        return strategy.apply(bufferedImage);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
