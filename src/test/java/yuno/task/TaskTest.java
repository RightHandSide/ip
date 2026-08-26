package yuno.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TaskTest {
    @Test
    void markDone_incompleteTask_setsCompletedStatus() {
        Task task = new Todo("read book", false);

        task.markDone();

        assertEquals('X', task.getStatus());
    }

    @Test
    void unmarkDone_completedTask_setsIncompleteStatus() {
        Task task = new Todo("read book", true);

        task.unmarkDone();

        assertEquals(' ', task.getStatus());
    }

    @Test
    void toString_task_returnsStatusAndDescription() {
        Task task = new Todo("read book", true);

        assertEquals("[T][X] read book", task.toString());
    }
}
