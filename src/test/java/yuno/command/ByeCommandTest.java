package yuno.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import yuno.exception.InvalidCommandFormatException;

class ByeCommandTest extends CommandTestSupport {
    @Test
    void execute_noArguments_printsByeAndStops() throws InvalidCommandFormatException {
        boolean shouldContinue = new ByeCommand("").execute(taskList, ui, storage);

        assertFalse(shouldContinue);
        assertTrue(ui.isByePrinted());
    }

    @Test
    void execute_additionalArguments_throwsInvalidCommandFormatException() {
        assertThrows(
                InvalidCommandFormatException.class, () ->
                        new ByeCommand("now").execute(taskList, ui, storage));
        assertFalse(ui.isByePrinted());
    }
}
