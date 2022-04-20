import cli.CLI;

public class Main {
    public static void main(String[] args) {
        CLI cli = new CLI();

        try {
            // Prepare cli with arguments from java standard args variable
            cli.prepare(joinArgs(args));

            // Execute with prepared arguments
            cli.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String joinArgs(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (String arg : args) builder.append(arg).append(" ");
        return builder.toString();
    }
}
