package editor;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import transformations.Transformation;
import transformations.lib.Brightness;
import transformations.lib.HueRotate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageEditorTest {
    private final ImageEditor editor = new ImageEditor();

    private final String inputPath = new File("src/test/java/resources/image.jpeg").getAbsolutePath();
    private final String outputPath = new File("src/test/java/resources/edited.jpeg").getAbsolutePath();

    @Test
    @Order(1)
    @DisplayName("Should open image")
    public void openImageTest() {
        Assertions.assertDoesNotThrow(() -> editor.openImage(inputPath));
    }

    @Test
    @Order(2)
    @DisplayName("Should apply transformations")
    public void applyTransformationsTest() throws IOException {
        Assertions.assertDoesNotThrow(() -> editor.openImage(inputPath));

        List<Transformation> transformations = new ArrayList<>();

        transformations.add(new Brightness(150));
        transformations.add(new HueRotate(180));

        Assertions.assertDoesNotThrow(() -> editor.applyTransformations(transformations));

        editor.saveImage(outputPath);
    }
}
