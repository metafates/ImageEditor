package transformations.lib;

import transformations.Transformation;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 * Sepia transformation
 */
public class Sepia extends Transformation {
    public Sepia(final int intensity) {
        super(image -> sepiaTransformation(image, intensity));
    }

    /**
     * @param image          Image to apply sepia
     * @param sepiaIntensity From 0-255, 30 produces nice results
     */
    private static BufferedImage sepiaTransformation(final BufferedImage image, int sepiaIntensity) {
        // 20 works well and recommended. 0 produces black & white image
        int sepiaDepth = 20;

        int w = image.getWidth();
        int h = image.getHeight();

        WritableRaster raster = image.getRaster();

        // We need 3 integers (for R,G,B color values) per pixel.
        int[] pixels = new int[w * h * 3];
        raster.getPixels(0, 0, w, h, pixels);

        // Process 3 ints at a time for each pixel.
        // Each pixel has 3 RGB colors in array
        for (int i = 0; i < pixels.length; i += 3) {
            int r = pixels[i];
            int g = pixels[i + 1];
            int b = pixels[i + 2];

            int gry = (r + g + b) / 3;
            r = g = b = gry;
            r = r + (sepiaDepth * 2);
            g = g + sepiaDepth;

            if (r > 255) r = 255;
            if (g > 255) g = 255;
            if (b > 255) b = 255;

            // Darken blue color to increase sepia effect
            b -= sepiaIntensity;

            // normalize if out of bounds
            if (b < 0) b = 0;
            if (b > 255) b = 255;

            pixels[i] = r;
            pixels[i + 1] = g;
            pixels[i + 2] = b;
        }
        raster.setPixels(0, 0, w, h, pixels);

        return image;
    }
}
