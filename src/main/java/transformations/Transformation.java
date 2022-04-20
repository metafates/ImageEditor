package transformations;

import java.awt.image.BufferedImage;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Base transformation class that applies given function to the image.
 * There are 2 types of transformations: pure and non-pure.
 * Pure transformation do not modify original image but rather return a copy with applied transformation.
 * Non-pure transformations does the opposite
 *
 * @see <a href="https://en.wikipedia.org/wiki/Pure_function">Pure functions</a>
 */
public class Transformation {
    private final boolean pure;
    private Function<BufferedImage, BufferedImage> pureTransformation;
    private Consumer<BufferedImage> nonPureTransformation;

    public Transformation(final Function<BufferedImage, BufferedImage> transformation) {
        this.pureTransformation = transformation;
        this.pure = true;
    }

    public Transformation(final Consumer<BufferedImage> transformation) {
        this.nonPureTransformation = transformation;
        this.pure = false;
    }

    public BufferedImage apply(BufferedImage bufferedImage) {
        if (pure) {
            return pureTransformation.apply(bufferedImage);
        }

        nonPureTransformation.accept(bufferedImage);
        return bufferedImage;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
