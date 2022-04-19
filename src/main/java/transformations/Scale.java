package main.java.transformations;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Function;

/**
 * Scales image by the given multiplier
 */
public class Scale extends Transformation {
    public Scale(final float multiplier) {
        super((Function<BufferedImage, BufferedImage>) image -> scaleTransformation(image, multiplier));
    }

    private static BufferedImage scaleTransformation(final BufferedImage image, final float multiplier) {
        int targetWidth = (int) (image.getWidth() * multiplier);
        int targetHeight = (int) (image.getHeight() * multiplier);

        Image resultingImage = image.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);

        return outputImage;
    }

    @Override
    public String toString() {
        return "Scale";
    }
}
