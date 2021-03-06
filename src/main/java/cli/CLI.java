package cli;

import argparser.Argument;
import argparser.Parser;
import editor.ImageEditor;
import presets.Preset;
import transformations.Transformation;
import transformations.TransformationFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * CLI class that provides interface for image editor class
 *
 * @see ImageEditor
 * @see Helper
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
        System.out.println(Helper.help());
        System.exit(0);
    }

    /**
     * Parses and applies given input string with arguments.
     * Can be used as <code>cli.prepare("--option=value").prepare("--option2 --option3=value")</code>
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
                case PRESET -> handlePreset(argument.value());
                default -> transformations.add(TransformationFactory.create(argument));
            }
        }

        return this;
    }

    /**
     * Extract transformations from preset and convert them to appropriate type
     *
     * @param presetName Preset Name
     */
    private void handlePreset(String presetName) {
        Preset preset = Preset.fromName(presetName);
        transformations.addAll(List.of(preset.getTransformations()));
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
        if (arguments.isEmpty() || showHelp) showHelpMessageAndExit();

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

