package transformations.lib;

import transformations.Transformation;
import utils.ImageUtils;

import java.awt.image.BufferedImage;

public class Blue extends Transformation {
    public Blue(final float multiplier) {
        super(image -> blueTransform(image, multiplier));
    }

    private static BufferedImage blueTransform(final BufferedImage image, final float multiplier) {
        ImageUtils.forEachPixel(image, rgb -> {
            rgb[2] = (int) Math.max(0, Math.min(255, rgb[2] * multiplier));
            return rgb;
        });

        return image;
    }
}
