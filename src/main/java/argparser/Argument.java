package main.java.argparser;

/**
 * Argument record that hold {@link Option} and its value
 *
 * @param option Argument {@link Option}
 * @param value  Argument value
 * @see Option
 * @see Parser
 */
public record Argument(Option option, String value) {

    public Option getOption() {
        return option;
    }

    public String getValue() {
        return value;
    }

    public int intValue() {
        return Integer.parseInt(value);
    }

    @Override
    public String toString() {
        if (option.requiresValue())
            return String.format("%s = %s", getOption(), getValue());

        return getOption().toString();
    }
}
