package main.java.transformations;

import java.awt.image.BufferedImage;

public class Inverse extends Transformation {
    public Inverse() {
        super(Inverse::inverseTransformation);
    }

    private static void inverseTransformation(final BufferedImage image) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        int[] RGB;

        for (int x = 0; x < imageWidth; x++) {
            for (int y = 0; y < imageHeight; y++) {
                RGB = image.getRaster().getPixel(x, y, new int[3]);

                int red = 255 - RGB[0];
                int green = 255 - RGB[1];
                int blue = 255 - RGB[2];


                int[] adjustedRGB = {red, green, blue};

                image.getRaster().setPixel(x, y, adjustedRGB);
            }
        }
    }

    @Override
    public String toString() {
        return "Inverse";
    }
}
