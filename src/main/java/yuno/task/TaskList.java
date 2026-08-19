package yuno.task;

import yuno.exception.InvalidTaskNumberException;

/**
 * Stores and manages the tasks entered during one chatbot session.
 */
public class TaskList {
    /** Stores the tasks in the order they were added. */
    private Task[] tasks;
    /** Records the number of tasks currently stored. */
    private int taskCount;

    /**
     * Creates an empty task list with space for up to 100 tasks.
     */
    public TaskList() {
        tasks = new Task[100];
        taskCount = 0;
    }

    public int getCount() {
        return taskCount;
    }

    /**
     * Returns the task at the specified one-based position.
     *
     * @param index One-based position of the task.
     * @return Task at the specified position.
     * @throws InvalidTaskNumberException If the position is outside the task list.
     */
    public Task getTask(int index) throws InvalidTaskNumberException {
        if (index < 1 || index > getCount()) {
            throw new InvalidTaskNumberException(
                    "Are you wasting my time? The integer you gave is out of bounds.");
        }
        return tasks[index - 1];
    }

    /**
     * Adds a new incomplete task with the specified description.
     *
     * @param description Description of the task to add.
     * @return Added to-do task.
     */
    public Task addTask(String description) {
        Todo addedTask = new Todo(description, false);
        tasks[taskCount] = addedTask;
        taskCount += 1;
        return addedTask;
    }

    /**
     * Adds a new incomplete deadline task with the specified description and deadline.
     *
     * @param description Description of the task to add.
     * @param deadline Deadline of the task as text.
     * @return Added deadline task.
     */
    public Task addTask(String description, String deadline) {
        Deadline addedTask = new Deadline(description, false, deadline);
        tasks[taskCount] = addedTask;
        taskCount += 1;
        return addedTask;
    }

    /**
     * Adds a new incomplete event task with the specified description and times.
     *
     * @param description Description of the task to add.
     * @param start Event start time as text.
     * @param end Event end time as text.
     * @return Added event task.
     */
    public Task addTask(String description, String start, String end) {
        Event addedTask = new Event(description, false, start, end);
        tasks[taskCount] = addedTask;
        taskCount += 1;
        return addedTask;
    }

    /**
     * Marks the task at the specified one-based position as completed.
     *
     * @param index One-based position of the task to mark.
     * @throws InvalidTaskNumberException If the position is outside the task list.
     */
    public void markTask(int index) throws InvalidTaskNumberException {
        getTask(index).markDone();
    }

    /**
     * Marks the task at the specified one-based position as incomplete.
     *
     * @param index One-based position of the task to unmark.
     * @throws InvalidTaskNumberException If the position is outside the task list.
     */
    public void unmarkTask(int index) throws InvalidTaskNumberException {
        getTask(index).unmarkDone();
    }
}
