package yuno.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;

import yuno.exception.InvalidCommandFormatException;
import yuno.exception.YunoException;

class ClearCommandTest extends CommandTestSupport {
    @Test
    void execute_noArguments_clearsAndSavesTasks() throws YunoException, IOException {
        taskList.addTask("read book");

        CommandResult commandResult = new ClearCommand("").execute(taskList, ui, storage);

        assertEquals(CommandResult.CONTINUE, commandResult);
        assertEquals(0, taskList.getCount());
        assertTrue(Files.readString(tempDir.resolve("tasks.txt")).isEmpty());
        assertTrue(ui.areTasksClearedPrinted());
    }

    @Test
    void execute_additionalArguments_throwsWithoutClearingTasks() {
        taskList.addTask("read book");

        assertThrows(
                InvalidCommandFormatException.class, () ->
                        new ClearCommand("all").execute(taskList, ui, storage));
        assertEquals(1, taskList.getCount());
    }
}
