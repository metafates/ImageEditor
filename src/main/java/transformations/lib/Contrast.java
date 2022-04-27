package transformations.lib;

import transformations.Transformation;
import util.Images;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Contrast extends Transformation {

    public Contrast() {
        super(Contrast::contrastTransformation);
    }

    private static BufferedImage contrastTransformation(final BufferedImage image) {
        Images.forEachPixel(image, rgb -> {
            int min = Arrays.stream(rgb).min().getAsInt();
            int max = Arrays.stream(rgb).max().getAsInt();

            int piv = min + max;

            rgb[0] = piv - rgb[0];
            rgb[1] = piv - rgb[1];
            rgb[2] = piv - rgb[2];

            return rgb;
        });

        return image;
    }
}
