package main.java.editor;

import main.java.transformations.Transformation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Image editor that can apply given transformations.
 * Supports method chaining
 */
public class ImageEditor {
    private BufferedImage image;
    private String path;

    public ImageEditor openImage(String absolutePath) throws IOException {
        image = ImageIO.read(new File(absolutePath));
        path = absolutePath;

        return this;
    }

    public ImageEditor saveImage() throws IOException {
        return saveImage(path);
    }

    public ImageEditor saveImage(String absolutePath) throws IOException {
        String[] chunks = absolutePath.split("\\.");
        String outImageExtension = chunks[chunks.length - 1];

        File outFile = new File(absolutePath);

        ImageIO.write(image, outImageExtension, outFile);

        return this;
    }

    public ImageEditor applyTransformation(Transformation transformation) {
        transformation.accept(image);

        return this;
    }

    public ImageEditor applyTransformations(Collection<Transformation> transformations) {
        transformations.forEach(this::applyTransformation);

        return this;
    }
}