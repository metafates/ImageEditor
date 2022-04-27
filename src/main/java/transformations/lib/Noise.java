package transformations.lib;

import transformations.Transformation;
import util.Images;
import util.Random;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Adds noise to the image
 */
public class Noise extends Transformation {

    public Noise(final float chance) {
        super(image -> noiseTransformation(image, chance));
    }

    private static BufferedImage noiseTransformation(final BufferedImage image, final float chance) {
        Images.forEachPixel(image, rgb -> {
            if (Math.random() <= chance) {
                Color c = Random.color();
                return new int[]{c.getRed(), c.getGreen(), c.getBlue()};
            }

            return rgb;
        });

        return image;
    }
}
