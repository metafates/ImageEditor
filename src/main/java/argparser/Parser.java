package main.java.argparser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Argument parser class
 * TODO: Add support for whitespaces
 *
 * @see Argument
 * @see Option
 */
public class Parser {
    private static final String optionRegex = "--[A-Za-z]+";
    private static final String valueRegex = "[A-Za-z0-9./\\\\]+";

    /**
     * Parses string into arguments list
     *
     * @param toParse String to parse
     * @return Parsed {@link Argument}s list
     * @throws ParseException If given string is corrupted
     */
    public static List<Argument> parse(final String toParse) throws ParseException {
        List<Argument> arguments = new ArrayList<>();
        String[] argumentsString = toParse.trim().split("\s+");

        // Show help message if no arguments given
        if (argumentsString.length == 0) {
            arguments.add(new Argument(Option.HELP, null));
            return arguments;
        }

        // Iterate over string arguments and parse each one
        for (int i = 0; i < argumentsString.length; i++) {
            String argumentString = argumentsString[i];

            try {
                String[] tokens = argumentString.split("=");
                String optionString = tokens[0];
                Option option = Option.fromString(optionString);

                if (!option.requiresValue()) {
                    // Expect argument to be in a format like "--option"
                    if (!argumentString.matches(String.format("^%s$", optionRegex))) {
                        String message = "Option '%s' does not accept value but it was given";
                        throw new IllegalArgumentException(String.format(message, option));
                    }

                    arguments.add(new Argument(option, null));
                } else {
                    // Expect argument to be in a format like "--option=value"
                    if (!argumentString.matches(String.format("^%s=%s$", optionRegex, valueRegex))) {
                        String message = "Argument '%s' does not match argument format";
                        throw new IllegalArgumentException(String.format(message, argumentString));
                    }

                    String valueString = tokens[1];

                    arguments.add(new Argument(option, valueString));
                }

            } catch (IllegalArgumentException e) {
                throw new ParseException(e.getMessage(), i);
            }

        }

        return arguments;
    }

}
