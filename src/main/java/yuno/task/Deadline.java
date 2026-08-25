package yuno.task;

import java.time.LocalDateTime;

import yuno.util.DateTimeFormats;

/**
 * Represents a task that must be completed by a specified deadline.
 */
public class Deadline extends Task {
    /** Stores the date and time by which the task must be completed. */
    private final LocalDateTime deadlineDateTime;

    /**
     * Creates a deadline task with the specified description, status, and deadline.
     *
     * @param description Text that describes the task.
     * @param isDone Whether the task is completed.
     * @param deadlineDateTime Date and time by which the task must be completed.
     */
    public Deadline(String description, boolean isDone, LocalDateTime deadlineDateTime) {
        super(description, isDone);
        this.deadlineDateTime = deadlineDateTime;
    }

    public LocalDateTime getDeadlineDateTime() {
        return deadlineDateTime;
    }

    @Override
    public String toStorageString() {
        return String.format(
                "D | %c | %s | %s",
                getStatus(),
                getDescription(),
                DateTimeFormats.STORAGE_FORMATTER.format(getDeadlineDateTime()));
    }

    /**
     * Returns this deadline task in a display-ready format.
     *
     * @return Formatted deadline task description, status, and deadline.
     */
    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                DateTimeFormats.DISPLAY_FORMATTER.format(getDeadlineDateTime()));
    }
}
