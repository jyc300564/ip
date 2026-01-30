package shallowseek;

import org.junit.jupiter.api.Test;
import shallowseek.commands.EmptyCommand;
import shallowseek.exceptions.ShallowSeekException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    void parse_emptyInput_returnsEmptyCommand() throws Exception {
        Parser parser = new Parser();
        assertTrue(parser.parse("   ") instanceof EmptyCommand);
    }

    @Test
    void parse_unknownCommand_throwsShallowSeekException() {
        Parser parser = new Parser();
        assertThrows(ShallowSeekException.class, () -> parser.parse("DLE"));
    }
}
