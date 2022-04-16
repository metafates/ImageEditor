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
    BRIGHTNESS("brightness"),
    CROP("crop"),
    HELP("help"),
    ROTATE("rotate"),
    OUTPUT("out"),
    INPUT("in");

    private final String argumentString;

    Option(final String text) {
        this.argumentString = text;
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

        throw new IllegalArgumentException(String.format("Unknown argument '%s'", text));
    }
}
