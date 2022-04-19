package main.java.argparser;

import java.lang.module.FindException;

/**
 * Option is a part of argument that holds its name<br>
 * <p>
 * Example: --option=value
 * TODO: Add type check
 *
 * @see Argument
 * @see Parser
 */
public enum Option {
    BRIGHTNESS(
        "brightness",         // Name
        "Adjust image brightness",  // Description
        "Multiplier",               // Value description
        ValueType.FLOAT             // Value type
    ),
    PRESET(
        "preset",
        "Choose preset",
        "Preset name",
        ValueType.STRING
    ),
    RED(
        "red",
        "Adjust red color",
        "Multiplier",
        ValueType.FLOAT
    ),
    GREEN(
        "green",
        "Adjust green color",
        "Multiplier",
        ValueType.FLOAT
    ),
    BLUE(
        "blue",
        "Adjust blue color",
        "Multiplier",
        ValueType.FLOAT
    ),
    SEPIA(
        "sepia",
        "Apply sepia effect",
        "Intensity, from 0-255, 30 produces nice results",
        ValueType.INTEGER
    ),
    HUE(
        "hue",
        "Hue rotate colors",
        "Degree",
        ValueType.INTEGER
    ),
    INVERSE(
        "inverse",
        "Inverse image colors"
    ),
    CONTRAST(
        "contrast",
        "Apply contrast effect"
    ),
    CROP(
        "crop",
        "Crop image to the given size",
        "X:Y W:H",
        ValueType.STRING
    ),
    HELP(
        "help",
        "Show help message and exit"
    ),
    SCALE(
        "scale",
        "Scale image by the multiplier",
        "Multiplier",
        ValueType.FLOAT
    ),
    SATURATION(
        "saturation",
        "Change saturation of the image",
        "Multiplier",
        ValueType.FLOAT
    ),
    BLUR(
        "blur",
        "Apply Gaussian blur to the image",
        "Blur radius",
        ValueType.INTEGER
    ),
    ROTATE(
        "rotate",
        "Rotate image",
        "Angle",
        ValueType.INTEGER
    ),
    OUTPUT(
        "out",
        "Path to save resulted image",
        "Path",
        ValueType.STRING
    ),
    INPUT(
        "in",
        "Input image path",
        "Path",
        ValueType.STRING
    );

    private final String argumentString;
    private final boolean requiresValue;
    private final String description;
    private final String valueDescription;
    private final ValueType valueType;


    Option(final String name, final String description) {
        this.argumentString = name;
        this.requiresValue = false;
        this.description = description;
        this.valueType = ValueType.NONE;
        this.valueDescription = "";
    }

    Option(final String name, final String description, final String valueDescription, final ValueType valueType) {
        this.argumentString = name;
        this.requiresValue = true;
        this.description = description;
        this.valueType = valueType;
        this.valueDescription = valueDescription;
    }

    /**
     * Makes option enum from string
     *
     * @param text Possible option as string
     * @return Corresponding option enum
     * @throws FindException If the argument is unknown
     */
    public static Option fromString(final String text) throws IllegalArgumentException {
        for (Option option : Option.values()) {
            if (option.getArgumentString().equals(text)) {
                return option;
            }
        }

        throw new IllegalArgumentException(String.format("Unknown option '%s'", text));
    }

    public boolean requiresValue() {
        return requiresValue;
    }

    public String getArgumentString() {
        return "--" + argumentString;
    }

    public String getDescription() {
        return description;
    }

    public String getValueDescription() {
        return valueDescription;
    }

    public String getTypeName() {
        return valueType.getTypeName();
    }

    public boolean validateValue(final String value) {
        return valueType.validate(value);
    }

    @Override
    public String toString() {
        return String.format(
            "%s %s [%s %s]",
            getArgumentString(),
            getDescription(),
            getTypeName(),
            getValueDescription()
        );
    }
}
