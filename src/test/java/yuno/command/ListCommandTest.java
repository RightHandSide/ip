package yuno.command;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import yuno.exception.InvalidCommandFormatException;
import yuno.exception.YunoException;

class ListCommandTest extends CommandTestSupport {
    @Test
    void execute_noArguments_displaysTaskList() throws YunoException {
        taskList.addTask("read book");

        boolean shouldContinue = new ListCommand("").execute(taskList, ui, storage);

        assertTrue(shouldContinue);
        assertSame(taskList, ui.displayedTaskList);
    }

    @Test
    void execute_additionalArguments_throwsInvalidCommandFormatException() {
        assertThrows(
                InvalidCommandFormatException.class,
                () -> new ListCommand("all").execute(taskList, ui, storage));
    }
}
