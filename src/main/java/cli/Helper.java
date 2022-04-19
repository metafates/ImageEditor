package main.java.cli;

import main.java.argparser.Option;

/**
 * Helper class that generates help messages
 */
public class Helper {
    private static final String title = "Climage - CLI Image Editor";
    private static final String[] sections = {
        usage(),
        options()
    };

    private static String usage() {

        String valuedOption = "--option=value";
        String standaloneOption = "--option";

        return "usage:\n" +
            String.format("  Option with value    %s\n", valuedOption) +
            String.format("  Option without value %s\n", standaloneOption);
    }

    private static String options() {
        StringBuilder helpMessage = new StringBuilder();
        helpMessage.append("options:\n");

        for (Option option : Option.values()) {
            String entry = String.format(
                "%s %s [%s %s]",
                option.getArgumentString(),
                option.getDescription(),
                option.getTypeName(),
                option.getValueDescription()
            );

            helpMessage.append(String.format("  %s\n", entry));
        }

        return helpMessage.toString();
    }

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
