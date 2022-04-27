package transformations;

import argparser.Argument;
import transformations.lib.*;

import java.util.function.Supplier;

/**
 * Transformation factory that creates transformations described by the arguments
 */
public class TransformationFactory {
    /**
     * @param argument {@link Argument} that describes transformation
     * @return Transformation described by the {@link Argument}
     */
    public static Transformation create(Argument argument) throws IllegalArgumentException {
        Supplier<Integer> intValue = () -> Integer.parseInt(argument.value());
        Supplier<Float> floatValue = () -> Float.parseFloat(argument.value());


        return switch (argument.option()) {
            case ROTATE -> new Rotate(
                intValue.get()
            );
            case BRIGHTNESS -> new Brightness(
                floatValue.get()
            );
            case HUE -> new HueRotate(
                intValue.get()
            );
            case SCALE -> new Scale(
                floatValue.get()
            );
            case SATURATION -> new Saturation(
                floatValue.get()
            );
            case BLUR -> new Blur(
                intValue.get()
            );
            case SEPIA -> new Sepia(
                intValue.get()
            );
            case RED -> new Red(
                floatValue.get()
            );
            case GREEN -> new Green(
                floatValue.get()
            );
            case BLUE -> new Blue(
                floatValue.get()
            );
            case MEDIANFILTER -> new MedianFilter(
                intValue.get()
            );
            case INVERSE -> new Inverse();
            case CONTRAST -> new Contrast();
            default -> throw new IllegalArgumentException(
                String.format(
                    "'%s' transformation is not implemented",
                    argument.option()
                )
            );
        };
    }
}
