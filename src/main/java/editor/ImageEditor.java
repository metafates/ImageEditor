package editor;

import transformations.Transformation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Image editor that provides easy API for opening image, applying transformations and saving it.
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
        image = transformation.apply(image);

        return this;
    }

    public ImageEditor applyTransformations(List<Transformation> transformations) {
        transformations.forEach(this::applyTransformation);

        return this;
    }
}
