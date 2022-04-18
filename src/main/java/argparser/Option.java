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
        "--brightness",
        "Adjust image brightness",
        ValueType.INTEGER
    ),
    HUE(
        "--hue",
        "Hue rotate colors",
        ValueType.INTEGER
    ),
    CROP(
        "--crop",
        "Crop image to the given size",
        ValueType.STRING
    ),
    HELP(
        "--help",
        "Show help message and exit"
    ),
    ROTATE(
        "--rotate",
        "Rotate image",
        ValueType.INTEGER
    ),
    OUTPUT(
        "--out",
        "Path to save resulted image",
        ValueType.STRING
    ),
    INPUT(
        "--in",
        "Input image path",
        ValueType.STRING
    );

    private final String argumentString;
    private final boolean requiresValue;
    private final String description;
    private final ValueType valueType;


    Option(final String text, final String description) {
        this.argumentString = text;
        this.requiresValue = false;
        this.description = description;
        this.valueType = ValueType.NONE;
    }

    Option(final String text, final String description, final ValueType valueType) {
        this.argumentString = text;
        this.requiresValue = true;
        this.description = description;
        this.valueType = valueType;
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
            if (option.argumentString.equals(text)) {
                return option;
            }
        }

        throw new IllegalArgumentException(String.format("Unknown option '%s'", text));
    }

    public boolean requiresValue() {
        return requiresValue;
    }

    public String getArgumentString() {
        return argumentString;
    }

    public String getDescription() {
        return description;
    }

    public String getTypeName() {
        return valueType.getTypeName();
    }

    public boolean validateValue(final String value) {
        return valueType.validate(value);
    }
}
