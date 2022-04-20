package transformations;

import utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.function.Consumer;

public class Red extends Transformation {
    public Red(final float multiplier) {
        super((Consumer<BufferedImage>) image -> redTransform(image, multiplier));
    }

    private static void redTransform(final BufferedImage image, final float multiplier) {
        ImageUtils.forEachPixel(image, rgb -> {
            rgb[0] = (int) Math.max(0, Math.min(255, rgb[0] * multiplier));
            return rgb;
        });
    }
}
