package main.java.transformations;

import java.awt.image.BufferedImage;
import java.awt.image.Kernel;
import java.util.function.Function;

/**
 * Gaussian blur transformation
 *
 * @see <a href="https://en.wikipedia.org/wiki/Gaussian_blur">Gaussian Blur</a>
 */
public class Blur extends Transformation {
    private static final int CLAMP_EDGES = 255;
    private static final boolean alpha = true;
    private static final int WRAP_EDGES = 1;

    public Blur(final int radius) {
        super((Function<BufferedImage, BufferedImage>) image -> blurTransformation(image, radius));
    }

    private static BufferedImage blurTransformation(final BufferedImage image, final int radius) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage out = new BufferedImage(width, height, image.getType());

        int[] inPixels = new int[width * height];
        int[] outPixels = new int[width * height];
        image.getRGB(0, 0, width, height, inPixels, 0, width);
        Kernel kernel = makeKernel(radius);

        convolveAndTranspose(kernel, inPixels, outPixels, width, height, alpha, CLAMP_EDGES);
        convolveAndTranspose(kernel, outPixels, inPixels, height, width, alpha, CLAMP_EDGES);

        out.setRGB(0, 0, width, height, inPixels, 0, width);

        return out;
    }

    private static Kernel makeKernel(float radius) {
        int r = (int) Math.ceil(radius);
        int rows = r * 2 + 1;
        float[] matrix = new float[rows];
        float sigma = radius / 3;
        float sigma22 = 2 * sigma * sigma;
        float sigmaPi2 = (float) (2 * Math.PI * sigma);
        float sqrtSigmaPi2 = (float) Math.sqrt(sigmaPi2);
        float radius2 = radius * radius;
        float total = 0;
        int index = 0;
        for (int row = -r; row <= r; row++) {
            float distance = row * row;
            if (distance > radius2)
                matrix[index] = 0;
            else
                matrix[index] = (float) Math.exp(-(distance) / sigma22) / sqrtSigmaPi2;
            total += matrix[index];
            index++;
        }
        for (int i = 0; i < rows; i++)
            matrix[i] /= total;

        return new Kernel(rows, 1, matrix);
    }

    public static void convolveAndTranspose(Kernel kernel, int[] inPixels, int[] outPixels, int width, int height, boolean alpha, int edgeAction) {
        float[] matrix = kernel.getKernelData(null);
        int cols = kernel.getWidth();
        int cols2 = cols / 2;

        for (int y = 0; y < height; y++) {
            int index = y;
            int ioffset = y * width;
            for (int x = 0; x < width; x++) {
                float r = 0, g = 0, b = 0, a = 0;
                int moffset = cols2;
                for (int col = -cols2; col <= cols2; col++) {
                    float f = matrix[moffset + col];

                    if (f != 0) {
                        int ix = x + col;
                        if (ix < 0) {
                            if (edgeAction == CLAMP_EDGES)
                                ix = 0;
                            else if (edgeAction == WRAP_EDGES)
                                ix = (x + width) % width;
                        } else if (ix >= width) {
                            if (edgeAction == CLAMP_EDGES)
                                ix = width - 1;
                            else if (edgeAction == WRAP_EDGES)
                                ix = (x + width) % width;
                        }
                        int rgb = inPixels[ioffset + ix];
                        a += f * ((rgb >> 24) & 0xff);
                        r += f * ((rgb >> 16) & 0xff);
                        g += f * ((rgb >> 8) & 0xff);
                        b += f * (rgb & 0xff);
                    }
                }
                int ia = alpha ? clamp((int) (a + 0.5)) : 0xff;
                int ir = clamp((int) (r + 0.5));
                int ig = clamp((int) (g + 0.5));
                int ib = clamp((int) (b + 0.5));

                outPixels[index] = (ia << 24) | (ir << 16) | (ig << 8) | ib;
                index += height;
            }
        }
    }

    private static int clamp(double value) {
        if (value < 0) return 0;
        if (value > 255) return 255;

        return (int) (value + 0.5);
    }

    @Override
    public String toString() {
        return "Blur";
    }
}
