package main.java.cli;

import main.java.argparser.Argument;
import main.java.argparser.Option;
import main.java.argparser.Parser;
import main.java.editor.ImageEditor;
import main.java.transformations.Transformation;
import main.java.transformations.TransformationFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * CLI class that provides interface for image editor class
 *
 * @see ImageEditor
 */
public class CLI {
    private final Parser parser = new Parser();
    private final ImageEditor editor = new ImageEditor();

    private final List<Transformation> transformations = new ArrayList<>();
    private final List<Argument> arguments = new ArrayList<>();

    private String inputFile = "";
    private String outputFile = "";

    private boolean showHelp = false;

    private static void showHelpMessageAndExit() {
        String usage = generateUsageHelpMessage();
        String options = generateOptionsHelpMessage();
        String helpMessage = String.format("""
            Climage - CLI Image Editor
                        
            %s
                        
            %s
            """, usage, options);

        System.out.println(helpMessage);

        System.exit(0);
    }

    private static String generateUsageHelpMessage() {

        String valuedOption = "--option=value";
        String standaloneOption = "--option";

        return "usage:\n" +
            String.format("  Option with value    %s\n", valuedOption) +
            String.format("  Option without value %s\n", standaloneOption);
    }

    private static String generateOptionsHelpMessage() {
        StringBuilder helpMessage = new StringBuilder();
        helpMessage.append("options:\n");

        for (Option option : Option.values()) {
            String entry = String.format(
                "%s %s [%s]",
                option.getArgumentString(),
                option.getDescription(),
                option.getTypeName()
            );

            helpMessage.append(String.format("  %s\n", entry));
        }

        return helpMessage.toString();
    }

    /**
     * Parses and applies given input string with arguments.
     * Can be used as <code>cli.accept("--option=value").accept("--option2 --option3=value")</code>
     *
     * @param inputString String with arguments
     * @return Class instance to support method chaining
     * @throws ParseException When parsing was unsuccessful
     */
    public CLI prepare(String inputString) throws ParseException {
        List<Argument> newArguments = parser.parse(inputString);
        arguments.addAll(newArguments);

        // Separates misc commands and transformations
        for (Argument argument : newArguments) {
            switch (argument.option()) {
                case HELP -> showHelp = true;
                case INPUT -> inputFile = argument.value();
                case OUTPUT -> outputFile = argument.value();
                default -> transformations.add(TransformationFactory.create(argument));
            }
        }

        return this;
    }

    /**
     * Executes accepted arguments
     *
     * @return CLI instance to support method chaining
     * @throws Exception When something when wrong while applying transformations
     *                   or when validation failed
     */
    public CLI execute() throws Exception {
        // Shortcut when there is nothing to do
        if (arguments.isEmpty()) return this;
        if (showHelp) showHelpMessageAndExit();

        validate();

        editor
            .openImage(inputFile)
            .applyTransformations(transformations)
            .saveImage(outputFile);

        return this;
    }

    /**
     * Check if input & output files are given
     *
     * @throws Exception When they don't
     */
    private void validate() throws Exception {
        if (inputFile.isEmpty()) {
            throw new Exception("Input file is not specified");
        }

        if (outputFile.isEmpty()) {
            throw new Exception("Output file is not specified");
        }
    }
}

