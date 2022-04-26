package transformations;

import java.awt.image.BufferedImage;
import java.util.function.Function;

/**
 * Base transformation class that applies given strategy to the image.
 */
public class Transformation implements Function<BufferedImage, BufferedImage> {
    private final Function<BufferedImage, BufferedImage> strategy;

    public Transformation(final Function<BufferedImage, BufferedImage> strategy) {
        this.strategy = strategy;
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
