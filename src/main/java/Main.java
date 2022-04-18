package main.java;

import main.java.cli.CLI;

public class Main {
    public static void main(String[] args) {

        for (String arg : args) {
            System.out.printf("%s ", arg);
        }

        System.out.println();

        CLI cli = new CLI();

        try {
            for (String arg : args) {
                cli.prepare(arg);
            }

            cli.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
