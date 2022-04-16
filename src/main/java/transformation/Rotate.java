package main.java.transformation;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Rotates image by given degree
 */
public class Rotate extends Transformation {

    public Rotate(final int degree) {
        super(image -> rotateTransformation(image, degree));
    }

    private static void rotateTransformation(final BufferedImage image, final int degree) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        Graphics2D graphics2D = image.createGraphics();

        graphics2D.rotate(Math.toRadians(degree), imageWidth / 2.0, imageHeight / 2.0);
    }
}
