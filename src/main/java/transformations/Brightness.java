package main.java.transformations;

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
        // Color spectrum
        int[] RGB;

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        double modifier = brightness / 100.0;

        // For each pixel
        for (int x = 0; x < imageWidth; x++) {
            for (int y = 0; y < imageHeight; y++) {
                RGB = image.getRaster().getPixel(x, y, new int[3]);

                int red = truncate((int) (RGB[0] * modifier));
                int green = truncate((int) (RGB[1] * modifier));
                int blue = truncate((int) (RGB[1] * modifier));


                int[] adjustedRGB = {red, green, blue};

                image.getRaster().setPixel(x, y, adjustedRGB);
            }
        }
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
