package yuno.task;

/**
 * Represents a task that must be completed by a specified deadline.
 */
public class Deadline extends Task {
    /** Stores the deadline as text. */
    private String deadline;

    /**
     * Creates a deadline task with the specified description, status, and deadline.
     *
     * @param description Text that describes the task.
     * @param isDone Whether the task is completed.
     * @param deadline Deadline of the task as text.
     */
    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns this deadline task in a display-ready format.
     *
     * @return Formatted deadline task description, status, and deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
