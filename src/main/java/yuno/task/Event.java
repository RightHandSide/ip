package yuno.task;

/**
 * Represents a task that starts and ends at specified times.
 */
public class Event extends Task {
    /** Stores the event start time as text. */
    private String start;
    /** Stores the event end time as text. */
    private String end;

    /**
     * Creates an event task with the specified description, status, start time, and end time.
     *
     * @param description Text that describes the task.
     * @param isDone Whether the task is completed.
     * @param start Event start time as text.
     * @param end Event end time as text.
     */
    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns this event task in a display-ready format.
     *
     * @return Formatted event task description, status, start time, and end time.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start, this.end);
    }
}
