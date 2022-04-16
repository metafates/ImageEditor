package main.java.argparser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Argument parser class
 *
 * @see Argument
 * @see Option
 */
public class Parser {
    /**
     * Parses string into arguments list
     *
     * @param toParse String to parse
     * @return Parsed {@link Argument}s list
     * @throws ParseException If given string is corrupted
     */
    public static List<Argument> parse(final String toParse) throws ParseException {
        List<Argument> arguments = new ArrayList<>();
        String[] argumentsString = toParse.split(" ");

        // Show help message if no arguments given
        if (argumentsString.length == 0) {
            arguments.add(new Argument(Option.HELP, null));
            return arguments;
        }

        // Iterate over string arguments and parse each one
        for (int i = 0; i < argumentsString.length; i++) {
            String argumentString = argumentsString[i];

            try {
                // Except argument to be in a format like "--option=value"
                if (!argumentString.matches("--\\w+=\\w+")) {
                    throw new IllegalArgumentException(String.format("Argument '%s' does not match argument format", argumentString));
                }

                String[] tokens = argumentString.split("=");

                // Remove leading '--'
                String optionString = tokens[0].substring(2);
                String valueString = tokens[1];

                Option option = Option.fromString(optionString);

                arguments.add(new Argument(option, valueString));
            } catch (IllegalArgumentException e) {
                throw new ParseException(e.getMessage(), i);
            }

        }

        return arguments;
    }
}
