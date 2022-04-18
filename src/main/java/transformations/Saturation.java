package main.java.transformations;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

/**
 * Saturation transformation
 */
public class Saturation extends Transformation {
    public Saturation(final float multiplier) {
        super((Consumer<BufferedImage>) image -> saturationTransformation(image, multiplier));
    }

    private static void saturationTransformation(final BufferedImage image, final float multiplier) {
        int width = image.getWidth();
        int height = image.getHeight();

        int[] RGB;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                RGB = image.getRaster().getPixel(x, y, new int[3]);

                float[] hsb = Color.RGBtoHSB(RGB[0], RGB[1], RGB[2], null);
                float hue = hsb[0];
                float saturation = hsb[1];
                float brightness = hsb[2];

                saturation *= multiplier;

                int rgb = Color.HSBtoRGB(hue, saturation, brightness);

                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                int[] adjustedRGB = {red, green, blue};

                image.getRaster().setPixel(x, y, adjustedRGB);
            }
        }
    }
}
