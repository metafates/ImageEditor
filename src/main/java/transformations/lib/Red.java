package transformations.lib;

import transformations.Transformation;
import util.Images;

import java.awt.image.BufferedImage;

public class Red extends Transformation {
    public Red(final float multiplier) {
        super(image -> redTransform(image, multiplier));
    }

    private static BufferedImage redTransform(final BufferedImage image, final float multiplier) {
        Images.forEachPixel(image, rgb -> {
            rgb[0] = (int) Math.max(0, Math.min(255, rgb[0] * multiplier));
            return rgb;
        });

        return image;
    }
}
