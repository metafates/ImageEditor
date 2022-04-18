package main.java;

import main.java.cli.CLI;

public class Main {
    public static void main(String[] args) {
        CLI cli = new CLI();

        try {
            // Prepare cli with arguments from java standard args variable
            for (String arg : args) {
                cli.prepare(arg);
            }

            // Execute with prepared arguments
            cli.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
