package util;

import java.awt.image.BufferedImage;
import java.util.function.Function;

/**
 * Image utils class
 */
public class Images {
    /**
     * Apply function for each pixel of the image
     *
     * @param image Image to work with
     * @param fn    Function that accepts rgb value of pixel and returns it
     */
    public static void forEachPixel(BufferedImage image, Function<int[], int[]> fn) {
        int height = image.getHeight();
        int width = image.getWidth();

        int[] rgb;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                rgb = image.getRaster().getPixel(x, y, new int[3]);

                image.getRaster().setPixel(x, y, fn.apply(rgb));
            }
        }
    }
}
