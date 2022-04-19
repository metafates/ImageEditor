package main.java.transformations;

import main.java.utils.ImageModificator;

import java.awt.image.BufferedImage;
import java.util.function.Consumer;

/**
 * Adjusts image brightness
 */
public class Brightness extends Transformation {
    public Brightness(final int brightness) {
        super((Consumer<BufferedImage>) image -> brightnessTransformation(image, brightness));
    }

    /**
     * Adjusts brightness of each pixel of the image
     *
     * @param image      Image to work with
     * @param brightness New brightness (%)
     */
    private static void brightnessTransformation(final BufferedImage image, final int brightness) {
        double modifier = brightness / 100.0;

        ImageModificator.forEachPixel(image, rgb -> {
            int red = truncate((int) (rgb[0] * modifier));
            int green = truncate((int) (rgb[1] * modifier));
            int blue = truncate((int) (rgb[1] * modifier));

            return new int[]{red, green, blue};
        });
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
