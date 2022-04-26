package transformations.lib;

import transformations.Transformation;
import utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Saturation transformation
 */
public class Saturation extends Transformation {
    public Saturation(final float multiplier) {
        super(image -> saturationTransformation(image, multiplier));
    }

    private static BufferedImage saturationTransformation(final BufferedImage image, final float multiplier) {
        ImageUtils.forEachPixel(image, rgb -> {
            float[] hsb = Color.RGBtoHSB(rgb[0], rgb[1], rgb[2], null);
            float hue = hsb[0];
            float saturation = hsb[1];
            float brightness = hsb[2];

            saturation *= multiplier;

            int rgb_ = Color.HSBtoRGB(hue, saturation, brightness);

            int red = (rgb_ >> 16) & 0xFF;
            int green = (rgb_ >> 8) & 0xFF;
            int blue = rgb_ & 0xFF;

            return new int[]{red, green, blue};
        });

        return image;
    }
}
