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
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private final String digits = "0123456789";
    private final String alphanumeric = String.format("%s%s%s-.", alphabet.toUpperCase(), alphabet, digits);

    private int cursor = -1;
    private char character = '\0';
    private String text;

    private void advance() {
        cursor++;
        if (cursor < text.length()) {
            character = text.charAt(cursor);
        } else {
            character = '\0';
        }
    }

    private char peek() {
        if (cursor + 1 < text.length()) {
            return text.charAt(cursor + 1);
        }

        return '\0';
    }

    private void setText(String text) {
        this.text = text;
    }

    private void resetText() {
        this.text = null;
    }

    private void resetCursor() {
        this.cursor = -1;
    }

    private void resetChar() {
        this.character = '\0';
    }

    /**
     * Parses string into arguments list
     *
     * @param toParse String to parse
     * @return Parsed {@link Argument}s list
     * @throws ParseException If given string is corrupted
     */
    public List<Argument> parse(final String toParse) throws ParseException {
        try {
            setText(toParse);
            advance();

            List<Argument> arguments = new ArrayList<>();

            while (character != '\0') {
                arguments.add(parseArgument());
            }

            // Show help message if no arguments were given
            if (arguments.size() == 0) {
                arguments.add(new Argument(Option.HELP, null));
            }

            return arguments;
        } catch (ParseException e) {
            throw e;
        } finally {
            reset(); // Prepare for the next usage
        }
    }

    /**
     * Prepares parser for the next input
     */
    private void reset() {
        resetText();
        resetCursor();
        resetChar();
    }

    /**
     * Parses argument by parsing option and value
     *
     * @return Argument
     * @throws ParseException If something went wrong
     */
    private Argument parseArgument() throws ParseException {
        while (character != '-' && character != '\0') {
            advance();
        }

        if (character == '\0') {
            throw new ParseException("Argument prefix expected", cursor);
        }

        Option option = parseOption();
        if (!option.requiresValue()) {
            return new Argument(option, null);
        }

        if (character != '=') {
            reset();
            throw new ParseException("'=' expected", cursor);
        }

        advance(); // Skip '=' char

        if (character == '\0') {
            throw new ParseException(String.format("Option '%s' expects a value", option), cursor);
        }

        String value = parseValue();

        return new Argument(option, value);
    }

    /**
     * Parses {@link Option}
     *
     * @return Parsed option
     * @throws ParseException If unknown option or invalid character met
     */
    private Option parseOption() throws ParseException {
        StringBuilder optionStringBuilder = new StringBuilder();
        while (character != '=' && character != ' ' && character != '\0') {
            if (alphanumeric.contains(String.valueOf(character))) {
                optionStringBuilder.append(character);
                advance();
            } else {
                throw new ParseException(String.format("Invalid character '%c'", character), cursor);
            }
        }

        try {
            return Option.fromString(optionStringBuilder.toString());
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage(), cursor);
        }

    }

    /**
     * Parses value. Supports escaping and parenthesis
     *
     * @return Parsed value
     * @throws ParseException On parenthesis mismatch
     */
    private String parseValue() throws ParseException {
        StringBuilder valueStringBuilder = new StringBuilder();

        char openingPar = '\0';

        // If value has parenthesis
        boolean hasPar = false;
        boolean parClosed = false;

        if (character == '"' || character == '\'') {
            openingPar = character;
            hasPar = true;
            advance();
        }

        while (character != '\0') {
            if (!hasPar && character == ' ') break;

            // Escape symbol
            if (character == '\\' && peek() == openingPar) {
                valueStringBuilder.append(openingPar);
                advance(); // Skip escape symbol
                advance(); // Skip escaped parenthesis since it's added already


                if (character == '\0') break;
            }

            if (character == openingPar) {
                advance();
                parClosed = true;
                break;
            }

            valueStringBuilder.append(character);
            advance();
        }

        if (hasPar && !parClosed) {
            throw new ParseException("Parenthesis mismatch", cursor);
        }

        return valueStringBuilder.toString();
    }
}
