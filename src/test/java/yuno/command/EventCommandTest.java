package yuno.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import yuno.exception.InvalidCommandFormatException;
import yuno.exception.YunoException;
import yuno.task.Event;

class EventCommandTest extends CommandTestSupport {
    @Test
    void execute_validEvent_addsEvent() throws YunoException {
        boolean shouldContinue = new EventCommand(
                "meeting /from 2026-08-30 0900 /to 2026-08-30 1030")
                .execute(taskList, ui, storage);

        Event event = assertInstanceOf(Event.class, taskList.getTask(1));
        assertTrue(shouldContinue);
        assertEquals(LocalDateTime.of(2026, 8, 30, 9, 0), event.getStartDateTime());
        assertEquals(LocalDateTime.of(2026, 8, 30, 10, 30), event.getEndDateTime());
        assertEquals(event, ui.addedTask);
    }

    @Test
    void execute_missingDescription_throwsInvalidCommandFormatException() {
        assertThrows(
                InvalidCommandFormatException.class,
                () -> new EventCommand(" /from 2026-08-30 /to 2026-08-31")
                        .execute(taskList, ui, storage));
    }

    @Test
    void execute_missingEnd_throwsInvalidCommandFormatException() {
        assertThrows(
                InvalidCommandFormatException.class,
                () -> new EventCommand("meeting /from 2026-08-30")
                        .execute(taskList, ui, storage));
    }

    @Test
    void execute_toBeforeFrom_throwsInvalidCommandFormatException() {
        assertThrows(
                InvalidCommandFormatException.class,
                () -> new EventCommand("meeting /to 2026-08-31 /from 2026-08-30")
                        .execute(taskList, ui, storage));
    }

    @Test
    void execute_endBeforeStart_throwsInvalidCommandFormatException() {
        assertThrows(
                InvalidCommandFormatException.class,
                () -> new EventCommand("meeting /from 2026-08-31 /to 2026-08-30")
                        .execute(taskList, ui, storage));
    }
}
