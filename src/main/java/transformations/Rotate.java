package main.java.transformations;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Function;

/**
 * Rotates image by given degree
 */
public class Rotate extends Transformation {

    public Rotate(final int angle) {
        super((Function<BufferedImage, BufferedImage>) image -> rotateTransformation(image, angle));
    }

    private static BufferedImage rotateTransformation(final BufferedImage image, final int angle) {
        double sin = Math.abs(Math.sin(Math.toRadians(angle)));
        double cos = Math.abs(Math.cos(Math.toRadians(angle)));

        int w = image.getWidth();
        int h = image.getHeight();

        int newW = (int) Math.floor(w * cos + h * sin);
        int newH = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newW, newH, image.getType());
        Graphics2D graphic = rotated.createGraphics();

        graphic.translate((newW - w) / 2, (newH - h) / 2);
        graphic.rotate(Math.toRadians(angle), w / 2.0, h / 2.0);
        graphic.drawRenderedImage(image, null);
        graphic.dispose();

        return rotated;
    }

    @Override
    public String toString() {
        return "Rotate";
    }
}
