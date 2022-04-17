package test.java.argparser;

import main.java.argparser.Argument;
import main.java.argparser.Option;
import main.java.argparser.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.List;


class ParserTest {

    @Test
    @DisplayName("Parse valid arguments")
    void parseValidArguments() throws ParseException {

        int hueRotateDegree = 190;

        String input = String.format("   --help   --crop=90  --hue=%d", hueRotateDegree);


        Assertions.assertDoesNotThrow(() -> Parser.parse(input));

        List<Argument> arguments = Parser.parse(input);

        Assertions.assertAll(
            () -> Assertions.assertFalse(arguments.get(0).getOption().requiresValue()),
            () -> Assertions.assertEquals(Option.CROP, arguments.get(1).getOption()),
            () -> Assertions.assertEquals(String.valueOf(hueRotateDegree), arguments.get(2).getValue())
        );
    }

    @Test
    @DisplayName("Throw error when parsing unknown arguments")
    void parseUnknownArguments() {
        String[] inputsWithUnknownArguments = {
            "--unknown=value",
            "--help --arg --crop90",
            "--crоp=90"
        };

        for (String input : inputsWithUnknownArguments) {
            Assertions.assertThrows(ParseException.class, () -> Parser.parse(input));
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
            "hue=90",
            "--hue=",
            "--help=",
            "\uD83E\uDD16",
            "\n\n\n\n***2==2904",
            "--hue=$$2",
            "==hue-20"
        };

        for (String input : malformedInputs) {
            Assertions.assertThrows(ParseException.class, () -> Parser.parse(input));
        }
    }
}