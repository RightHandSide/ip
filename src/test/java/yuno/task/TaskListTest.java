package yuno.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import yuno.exception.InvalidTaskNumberException;

class TaskListTest {
    @Test
    void addTask_validTodoDescription_addsTodoTask() throws InvalidTaskNumberException {
        TaskList tasks = new TaskList();
        Todo addedTask = tasks.addTask("addedTask");

        assertEquals(1, tasks.getCount());
        assertEquals("addedTask", addedTask.getDescription());
        assertEquals("addedTask", tasks.getTask(1).getDescription());
    }

    @Test
    void addTask_validDeadlineDescription_addsDeadlineTask() throws InvalidTaskNumberException {
        TaskList tasks = new TaskList();
        LocalDateTime addedDate = LocalDateTime.of(2026, 8, 25, 12, 0);
        Deadline addedTask = tasks.addTask("addedTask", addedDate);

        assertEquals(1, tasks.getCount());
        assertEquals(addedDate, addedTask.getDeadlineDateTime());
        assertEquals("addedTask", addedTask.getDescription());
        assertEquals("addedTask", tasks.getTask(1).getDescription());
    }

    @Test
    void addTask_validEventDescription_addsEventTask() throws InvalidTaskNumberException {
        TaskList tasks = new TaskList();
        LocalDateTime startDate = LocalDateTime.of(2026, 8, 25, 12, 0);
        LocalDateTime endDate = LocalDateTime.of(2026, 8, 27, 12, 0);
        Event addedTask = tasks.addTask("addedTask", startDate, endDate);

        assertEquals(1, tasks.getCount());
        assertEquals(endDate, addedTask.getEndDateTime());
        assertEquals(startDate, addedTask.getStartDateTime());
        assertEquals("addedTask", addedTask.getDescription());
        assertEquals("addedTask", tasks.getTask(1).getDescription());
    }

    @Test
    void addTask_validTask_addsTask() throws InvalidTaskNumberException {
        TaskList tasks = new TaskList();
        Todo addedTask = new Todo("addedTask", true);
        tasks.addTask(addedTask);

        assertEquals(1, tasks.getCount());
        assertEquals(addedTask, tasks.getTask(1));
        assertEquals('X', tasks.getTask(1).getStatus());
    }

    @Test
    void getTask_validTaskNumber_returnsCorrectTask() throws InvalidTaskNumberException {
        TaskList tasks = new TaskList();
        tasks.addTask("addedTask");

        Task task = tasks.getTask(1);

        assertEquals("addedTask", task.getDescription());
    }

    @Test
    void getTask_invalidTaskNumber_throwsInvalidTaskNumberException() {
        TaskList tasks = new TaskList();
        tasks.addTask("addedTask");

        InvalidTaskNumberException firstException = assertThrows(
                InvalidTaskNumberException.class, () -> tasks.getTask(-1));
        InvalidTaskNumberException secondException = assertThrows(
                InvalidTaskNumberException.class, () -> tasks.getTask(0));
        InvalidTaskNumberException thirdException = assertThrows(
                InvalidTaskNumberException.class, () -> tasks.getTask(2));

        String expectedMessage =
                "Are you wasting my time? The integer you gave is out of bounds.";
        assertEquals(expectedMessage, firstException.getMessage());
        assertEquals(expectedMessage, secondException.getMessage());
        assertEquals(expectedMessage, thirdException.getMessage());
    }

    @Test
    void markTask_validTaskNumber_marksSpecifiedTask() throws InvalidTaskNumberException {
        TaskList tasks = new TaskList();
        tasks.addTask("first task");
        tasks.addTask("second task");

        tasks.markTask(2);

        assertEquals(' ', tasks.getTask(1).getStatus());
        assertEquals('X', tasks.getTask(2).getStatus());
    }

    @Test
    void markTask_invalidTaskNumber_throwsInvalidTaskNumberException() {
        TaskList tasks = new TaskList();

        assertThrows(InvalidTaskNumberException.class, () -> tasks.markTask(1));
    }

    @Test
    void unmarkTask_validTaskNumber_unmarksSpecifiedTask() throws InvalidTaskNumberException {
        TaskList tasks = new TaskList();
        Todo completedTask = new Todo("completed task", true);
        tasks.addTask(completedTask);

        tasks.unmarkTask(1);

        assertEquals(' ', tasks.getTask(1).getStatus());
    }

    @Test
    void unmarkTask_invalidTaskNumber_throwsInvalidTaskNumberException() {
        TaskList tasks = new TaskList();

        assertThrows(InvalidTaskNumberException.class, () -> tasks.unmarkTask(1));
    }

    @Test
    void deleteTask_validTaskNumber_removesAndReturnsSpecifiedTask() throws InvalidTaskNumberException {
        TaskList tasks = new TaskList();
        Todo firstTask = tasks.addTask("first task");
        Todo secondTask = tasks.addTask("second task");

        Task deletedTask = tasks.deleteTask(1);

        assertSame(firstTask, deletedTask);
        assertEquals(1, tasks.getCount());
        assertSame(secondTask, tasks.getTask(1));
    }

    @Test
    void deleteTask_invalidTaskNumber_throwsInvalidTaskNumberException() {
        TaskList tasks = new TaskList();

        InvalidTaskNumberException exception = assertThrows(
                InvalidTaskNumberException.class, () -> tasks.deleteTask(1));

        assertEquals(
                "What do you want me to delete, your brain? The integer you gave is out of bounds.",
                exception.getMessage());
    }

    @Test
    void clearTasks_nonEmptyTaskList_removesAllTasks() {
        TaskList tasks = new TaskList();
        tasks.addTask("first task");
        tasks.addTask("second task");

        tasks.clearTasks();

        assertEquals(0, tasks.getCount());
    }

    @Test
    void findTasksFor_mixedTasks_returnsRelevantTasksInOriginalOrder() {
        TaskList tasks = new TaskList();
        Todo todo = tasks.addTask("todo");
        Deadline earlierDeadline = tasks.addTask(
                "earlier deadline", LocalDateTime.of(2026, 8, 25, 12, 0));
        Deadline laterDeadline = tasks.addTask(
                "later deadline", LocalDateTime.of(2026, 8, 27, 12, 0));
        Event currentEvent = tasks.addTask(
                "current event",
                LocalDateTime.of(2026, 8, 25, 12, 0),
                LocalDateTime.of(2026, 8, 27, 12, 0));
        Event pastEvent = tasks.addTask(
                "past event",
                LocalDateTime.of(2026, 8, 23, 12, 0),
                LocalDateTime.of(2026, 8, 24, 12, 0));

        List<Task> matchingTasks = tasks.findTasksFor(LocalDate.of(2026, 8, 26));

        assertEquals(List.of(todo, earlierDeadline, currentEvent), matchingTasks);
        assertFalse(matchingTasks.contains(laterDeadline));
        assertFalse(matchingTasks.contains(pastEvent));
    }

    @Test
    void findTasksFor_noRelevantTasks_returnsEmptyList() {
        TaskList tasks = new TaskList();
        tasks.addTask(
                "future deadline", LocalDateTime.of(2026, 8, 27, 12, 0));

        List<Task> matchingTasks = tasks.findTasksFor(LocalDate.of(2026, 8, 26));

        assertTrue(matchingTasks.isEmpty());
    }

    @Test
    void findTasksFor_wordInMixedTasks_returnsSubstringMatchesInOriginalOrder() {
        TaskList tasks = new TaskList();
        Todo firstMatch = tasks.addTask("read project requirements");
        tasks.addTask("buy groceries");
        Deadline secondMatch = tasks.addTask(
                "submit project report", LocalDateTime.of(2026, 8, 30, 18, 0));
        Event thirdMatch = tasks.addTask(
                "project meeting",
                LocalDateTime.of(2026, 8, 31, 9, 0),
                LocalDateTime.of(2026, 8, 31, 10, 0));

        List<Task> matchingTasks = tasks.findTasksFor("project");

        assertEquals(List.of(firstMatch, secondMatch, thirdMatch), matchingTasks);
    }

    @Test
    void findTasksFor_wordNotInAnyDescription_returnsEmptyList() {
        TaskList tasks = new TaskList();
        tasks.addTask("read book");
        tasks.addTask("buy groceries");

        List<Task> matchingTasks = tasks.findTasksFor("project");

        assertTrue(matchingTasks.isEmpty());
    }
}
