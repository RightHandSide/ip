package yuno.task;

/**
 * Represents a task without a date or time.
 */
public class Todo extends Task {
    /**
     * Creates a to-do task with the specified description and completion status.
     *
     * @param description Text that describes the task.
     * @param isDone Whether the task is completed.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns this to-do task in a display-ready format.
     *
     * @return Formatted to-do task description and status.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
