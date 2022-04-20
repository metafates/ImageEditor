package transformations;

import utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.function.Consumer;

/**
 * Applies hue rotation to the image
 *
 * @see <a href="https://en.wikipedia.org/wiki/Hue">Hue</a>
 */
public class HueRotate extends Transformation {

    public HueRotate(final int degree) {
        super((Consumer<BufferedImage>) image -> hueRotateTransformation(image, degree));
    }

    private static void hueRotateTransformation(final BufferedImage image, final int degree) {
        double[][] rotationMatrix = generateRotationMatrix(degree);
        ImageUtils.forEachPixel(image, rgb -> rotateRGB(rotationMatrix, rgb));
    }

    private static double[][] generateRotationMatrix(int degree) {
        double cosA = Math.cos(Math.toRadians(degree));
        double sinA = Math.sin(Math.toRadians(degree));

        double U = 1. / 3. * (1.0 - cosA) - Math.sqrt(1. / 3.) * sinA;
        double D = 1. / 3. * (1.0 - cosA) + Math.sqrt(1. / 3.) * sinA;
        double V = cosA + 1. / 3. * (1.0 - cosA);
        double Q = cosA + (1.0 - cosA) / 3.0;

        return new double[][]{
            {Q, U, D},
            {D, V, U},
            {U, D, V}
        };
    }

    private static int[] rotateRGB(double[][] matrix, int[] rgb) {
        int r = rgb[0];
        int g = rgb[1];
        int b = rgb[2];

        double rx = r * matrix[0][0] + g * matrix[0][1] + b * matrix[0][2];
        double gx = r * matrix[1][0] + g * matrix[1][1] + b * matrix[1][2];
        double bx = r * matrix[2][0] + g * matrix[2][1] + b * matrix[2][2];

        return new int[]{clamp(rx), clamp(gx), clamp(bx)};
    }


    private static int clamp(double value) {
        if (value < 0) return 0;
        if (value > 255) return 255;

        return (int) (value + 0.5);
    }
}
