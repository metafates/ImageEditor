package transformations;

import utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.function.Consumer;

public class Blue extends Transformation {
    public Blue(final float multiplier) {
        super((Consumer<BufferedImage>) image -> blueTransform(image, multiplier));
    }

    private static void blueTransform(final BufferedImage image, final float multiplier) {
        ImageUtils.forEachPixel(image, rgb -> {
            rgb[2] = (int) Math.max(0, Math.min(255, rgb[2] * multiplier));
            return rgb;
        });
    }
}
