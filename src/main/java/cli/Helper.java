package main.java.cli;

import main.java.argparser.Option;
import main.java.presets.Preset;

/**
 * Helper class that generates help messages
 */
public class Helper {
    private static final String title = "Climage - CLI Image Editor";
    private static final String[] sections = {
        usage(),
        options(),
        presets()
    };

    /**
     * @return Usage help message
     */
    private static String usage() {

        String valuedOption = "--option=value";
        String standaloneOption = "--option";

        return "usage:\n" +
            String.format("  Option with value    %s\n", valuedOption) +
            String.format("  Option without value %s\n", standaloneOption);
    }

    /**
     * @return Presets names
     */
    private static String presets() {
        StringBuilder helpMessage = new StringBuilder();
        helpMessage.append("presets:\n");

        for (Preset preset : Preset.values()) {
            helpMessage.append(String.format("  %s\n", preset));
        }

        return helpMessage.toString();
    }

    /**
     * @return Options help message
     */
    private static String options() {
        StringBuilder helpMessage = new StringBuilder();
        helpMessage.append("options:\n");

        for (Option option : Option.values()) {
            helpMessage.append(String.format("  %s\n", option));
        }

        return helpMessage.toString();
    }

    /**
     * @return Generated help message
     */
    public static String help() {
        StringBuilder msg = new StringBuilder();

        msg.append(title);
        msg.append("\n");

        for (String section : sections) {
            msg.append("\n");
            msg.append(section);
        }

        return msg.toString();
    }
}
