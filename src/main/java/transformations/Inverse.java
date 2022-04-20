package transformations;

import utils.ImageUtils;

import java.awt.image.BufferedImage;

public class Inverse extends Transformation {
    public Inverse() {
        super(Inverse::inverseTransformation);
    }

    private static void inverseTransformation(final BufferedImage image) {
        ImageUtils.forEachPixel(image, rgb -> {
            int red = 255 - rgb[0];
            int green = 255 - rgb[1];
            int blue = 255 - rgb[2];

            return new int[]{red, green, blue};
        });
    }
}
