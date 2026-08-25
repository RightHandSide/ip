package yuno.task;

import java.time.LocalDate;

/**
 * Represents a task with a description and completion status.
 */
public abstract class Task {
    /** Stores the text that describes this task. */
    private final String description;
    /** Records whether this task has been completed. */
    private boolean isDone;

    /**
     * Creates a task with the specified description and completion status.
     *
     * @param description Text that describes the task.
     * @param isDone Whether the task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public char getStatus() {
        return (isDone ? 'X' : ' ');
    }

    /**
     * Marks this task as completed.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks this task as incomplete.
     */
    public void unmarkDone() {
        isDone = false;
    }

    /**
     * Returns whether this task is relevant for the specified date.
     *
     * @param date Date to check.
     * @return True if this task is relevant for the date; otherwise, false.
     */
    public abstract boolean isRelevantFor(LocalDate date);

    /**
     * Returns this task in the format used by the task data file.
     *
     * @return Storage-ready task data.
     */
    public abstract String toStorageString();

    /**
     * Returns this task in a display-ready format with its completion status.
     *
     * @return Formatted task description and status.
     */
    @Override
    public String toString() {
        return String.format("[%c] %s", getStatus(), getDescription());
    }
}
