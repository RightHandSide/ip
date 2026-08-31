package yuno.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import yuno.exception.InvalidCommandFormatException;
import yuno.exception.YunoException;
import yuno.task.Deadline;

class DeadlineCommandTest extends CommandTestSupport {
    @Test
    void execute_validDeadline_addsDeadline() throws YunoException {
        boolean shouldContinue = new DeadlineCommand("submit report /by 2026-08-30 1800")
                .execute(taskList, ui, storage);

        Deadline deadline = assertInstanceOf(Deadline.class, taskList.getTask(1));
        assertTrue(shouldContinue);
        assertEquals("submit report", deadline.getDescription());
        assertEquals(LocalDateTime.of(2026, 8, 30, 18, 0), deadline.getDeadlineDateTime());
        assertEquals(deadline, ui.getAddedTask());
    }

    @Test
    void execute_dateOnly_addsDeadlineAtMidnight() throws YunoException {
        new DeadlineCommand("submit report /by 2026-08-30").execute(taskList, ui, storage);

        Deadline deadline = (Deadline) taskList.getTask(1);
        assertEquals(LocalDateTime.of(2026, 8, 30, 0, 0), deadline.getDeadlineDateTime());
    }

    @Test
    void execute_missingDescription_throwsInvalidCommandFormatException() {
        assertThrows(
                InvalidCommandFormatException.class, () ->
                        new DeadlineCommand(" /by 2026-08-30").execute(taskList, ui, storage));
    }

    @Test
    void execute_missingDeadline_throwsInvalidCommandFormatException() {
        assertThrows(
                InvalidCommandFormatException.class, () ->
                        new DeadlineCommand("submit report").execute(taskList, ui, storage));
    }

    @Test
    void execute_invalidDeadline_throwsInvalidCommandFormatException() {
        assertThrows(
                InvalidCommandFormatException.class, () ->
                        new DeadlineCommand("submit report /by 2026-02-30")
                        .execute(taskList, ui, storage));
    }
}
