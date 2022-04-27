package transformations.lib;

import transformations.Transformation;
import util.Images;

import java.awt.image.BufferedImage;

public class Inverse extends Transformation {
    public Inverse() {
        super(Inverse::inverseTransformation);
    }

    private static BufferedImage inverseTransformation(final BufferedImage image) {
        Images.forEachPixel(image, rgb -> {
            int red = 255 - rgb[0];
            int green = 255 - rgb[1];
            int blue = 255 - rgb[2];

            return new int[]{red, green, blue};
        });

        return image;
    }
}
