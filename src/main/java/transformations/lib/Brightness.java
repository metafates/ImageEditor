package transformations.lib;

import transformations.Transformation;
import util.Images;

import java.awt.image.BufferedImage;

/**
 * Adjusts image brightness
 */
public class Brightness extends Transformation {
    public Brightness(final float brightness) {
        super(image -> brightnessTransformation(image, brightness));
    }

    /**
     * Adjusts brightness of each pixel of the image
     *
     * @param image    Image to work with
     * @param modifier brightness modifier
     */
    private static BufferedImage brightnessTransformation(final BufferedImage image, final float modifier) {
        Images.forEachPixel(image, rgb -> {
            int red = truncate((int) (rgb[0] * modifier));
            int green = truncate((int) (rgb[1] * modifier));
            int blue = truncate((int) (rgb[1] * modifier));

            return new int[]{red, green, blue};
        });

        return image;
    }

    /**
     * Truncates integer to fit in a 0...255 range
     *
     * @param value Value to truncate
     * @return Truncated value
     */
    private static int truncate(int value) {
        return Math.max(0, Math.min(255, value));
    }
}
