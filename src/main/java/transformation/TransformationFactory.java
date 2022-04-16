package main.java.transformation;

import main.java.argparser.Argument;
import main.java.wrappers.Size;

/**
 * Transformation factory that creates transformations described by the argument
 */
public class TransformationFactory {
    /**
     * @param argument {@link Argument} that describes transformation
     * @return Transformation described by {@link Argument}
     */
    public static Transformation create(Argument argument) throws IllegalArgumentException {

        switch (argument.option()) {
            case ROTATE -> new Rotate(Integer.parseInt(argument.value()));
            case CROP -> new Crop(new Size(argument.value()));
            case BRIGHTNESS -> new Brightness(Integer.parseInt(argument.value()));
            default -> throw new IllegalArgumentException(String.format("'%s' transformation is not implemented", argument.option()));
        }

        // Unreachable
        return null;
    }
}
