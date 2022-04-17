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
    BRIGHTNESS("--brightness", true),
    HUE("--hue", true),
    CROP("--crop", true),
    HELP("--help", false),
    ROTATE("--rotate", true),
    OUTPUT("--out", true),
    INPUT("--in", true);

    private final String argumentString;
    private final boolean requiresValue;

    Option(final String text, final boolean requiresValue) {
        this.argumentString = text;
        this.requiresValue = requiresValue;
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
}
