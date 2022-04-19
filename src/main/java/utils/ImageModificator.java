package main.java.utils;

import java.awt.image.BufferedImage;
import java.util.function.Function;

public class ImageModificator {
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
