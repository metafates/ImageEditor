package transformations;

import argparser.Argument;
import wrappers.Size;

/**
 * Transformation factory that creates transformations described by the arguments
 */
public class TransformationFactory {
    /**
     * @param argument {@link Argument} that describes transformation
     * @return Transformation described by the {@link Argument}
     */
    public static Transformation create(Argument argument) throws IllegalArgumentException {

        return switch (argument.option()) {
            case ROTATE -> new Rotate(argument.intValue());
            case CROP -> new Crop(new Size(argument.value()));
            case BRIGHTNESS -> new Brightness(argument.floatValue());
            case HUE -> new HueRotate(argument.intValue());
            case SCALE -> new Scale(argument.floatValue());
            case SATURATION -> new Saturation(argument.floatValue());
            case BLUR -> new Blur(argument.intValue());
            case SEPIA -> new Sepia(argument.intValue());
            case RED -> new Red(argument.floatValue());
            case GREEN -> new Green(argument.floatValue());
            case BLUE -> new Blue(argument.floatValue());
            case INVERSE -> new Inverse();
            case CONTRAST -> new Contrast();
            default -> throw new IllegalArgumentException(String.format("'%s' transformation is not implemented", argument.option()));
        };
    }
}
