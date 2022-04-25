package argparser;

/**
 * Argument record that hold {@link Option} and its value
 *
 * @param option Argument {@link Option}
 * @param value  Argument value
 * @see Option
 * @see Parser
 */
public record Argument(Option option, String value) {
    @Override
    public String toString() {
        if (option.requiresValue())
            return String.format("%s = %s", option, value);

        return option.toString();
    }
}
