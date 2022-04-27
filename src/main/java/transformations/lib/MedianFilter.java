package transformations.lib;

import transformations.Transformation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class MedianFilter extends Transformation {

    public MedianFilter(final int radius) {
        super(image -> medianFilterTransformation(image, radius));
    }

    private static BufferedImage medianFilterTransformation(final BufferedImage image, final int radius) {
        BufferedImage output = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        for (int x = radius; x < image.getWidth() - radius; x++) {
            for (int y = radius; y < image.getHeight() - radius; y++) {
                ArrayList<Color> neighbours = getNeighbours(image, x, y, radius);

                int size = neighbours.size();
                int[] reds = new int[size];
                int[] greens = new int[size];
                int[] blues = new int[size];

                for (int i = 0; i < neighbours.size(); i++) {
                    Color color = neighbours.get(i);

                    reds[i] = color.getRed();
                    greens[i] = color.getGreen();
                    blues[i] = color.getBlue();
                }

                Arrays.sort(reds);
                Arrays.sort(greens);
                Arrays.sort(blues);

                int medianIndex = neighbours.size() / 2;
                Color color = new Color(reds[medianIndex], greens[medianIndex], blues[medianIndex]);
                output.setRGB(x, y, color.getRGB());
            }
        }

        return output;
    }

    private static ArrayList<Color> getNeighbours(BufferedImage image, int x, int y, int radius) {
        ArrayList<Color> neighbours = new ArrayList<>();

        for (int x_ = -radius; x_ <= radius; x_++) {
            for (int y_ = -radius; y_ <= radius; y_++) {
                if (x_ == 0 && y_ == 0) continue;

                Color neighbour = new Color(image.getRGB(x + x_, y + y_));
                neighbours.add(neighbour);
            }
        }

        return neighbours;
    }
}
