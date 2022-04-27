package transformations.lib;


import transformations.Transformation;
import util.Images;

import java.awt.image.BufferedImage;

public class Green extends Transformation {
    public Green(final float multiplier) {
        super(image -> greenTransform(image, multiplier));
    }

    private static BufferedImage greenTransform(final BufferedImage image, final float multiplier) {
        Images.forEachPixel(image, rgb -> {
            rgb[1] = (int) Math.max(0, Math.min(255, rgb[1] * multiplier));
            return rgb;
        });

        return image;
    }
}
