package main.java.transformations;


import main.java.utils.ImageModificator;

import java.awt.image.BufferedImage;
import java.util.function.Consumer;

public class Green extends Transformation {
    public Green(final float multiplier) {
        super((Consumer<BufferedImage>) image -> greenTransform(image, multiplier));
    }

    private static void greenTransform(final BufferedImage image, final float multiplier) {
        ImageModificator.forEachPixel(image, rgb -> {
            rgb[1] = (int) Math.max(0, Math.min(255, rgb[1] * multiplier));
            return rgb;
        });
    }
}
