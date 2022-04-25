package argparser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.List;


class ParserTest {
    Parser parser = new Parser();

    @Test
    @DisplayName("Parse valid arguments")
    void parseValidArguments() throws ParseException {

        String input = " --in=\"file with whitespace.ext\" --help   --blur=90  --hue=190";

        Assertions.assertDoesNotThrow(() -> parser.parse(input));

        List<Argument> arguments = parser.parse(input);

        Assertions.assertAll(
            () -> Assertions.assertEquals("file with whitespace.ext", arguments.get(0).value()),
            () -> Assertions.assertFalse(arguments.get(1).option().requiresValue()),
            () -> Assertions.assertEquals("190", arguments.get(3).value())
        );
    }

    @Test
    @DisplayName("Throw error when parsing unknown arguments")
    void parseUnknownArguments() {
        String[] inputsWithUnknownArguments = {
            "--unknown=value",
            "--help --arg --crop90",
            "--crop=90" // 'о' symbol in 'crop' is a non-ascii symbol
        };

        for (String input : inputsWithUnknownArguments) {
            Assertions.assertThrows(ParseException.class, () -> parser.parse(input));
        }
    }

    @Test
    @DisplayName("Test escape symbols in values")
    void testEscapeSymbolsInValues() throws ParseException {
        String[] inputsWithEscapeSymbols = {
            // Would look like
            // --in="some long name with \"escaping\""
            "--in=\"some long name with \\\"escaping\\\"\"",
        };

        for (String input : inputsWithEscapeSymbols) {
            Assertions.assertDoesNotThrow(() -> parser.parse(input));

            List<Argument> arguments = parser.parse(input);

            Assertions.assertTrue(arguments.get(0).value().contains("\""));
        }
    }

    @Test
    @DisplayName("Throw error when parsing malformed arguments")
    void parseMalformedArguments() {
        String[] malformedInputs = {
            "-help",
            "--=",
            "--",
            "--hue=90 j",
            "--crop = name",
            "--in=na\"me",
            "hue=90",
            "--hue=",
            "--help=",
            "\uD83E\uDD16",
            "\n\n\n\n***2==2904",
            "==hue-20"
        };

        for (String input : malformedInputs) {
            Assertions.assertThrows(ParseException.class, () -> parser.parse(input));
        }
    }
}