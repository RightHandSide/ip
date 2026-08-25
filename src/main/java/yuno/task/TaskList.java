package yuno.task;

import java.util.ArrayList;
import java.util.List;

import yuno.exception.InvalidTaskNumberException;

/**
 * Stores and manages the tasks currently available to the chatbot.
 */
public class TaskList {
    /** Stores the tasks in the order they were added. */
    private final List<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int getCount() {
        return tasks.size();
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
            // Reject task numbers that do not identify an existing task.
            throw new InvalidTaskNumberException(
                    "Are you wasting my time? The integer you gave is out of bounds.");
        }
        return tasks.get(index - 1);
    }

    /**
     * Adds a new incomplete task with the specified description.
     *
     * @param description Description of the task to add.
     * @return Added to-do task.
     */
    public Task addTask(String description) {
        Todo addedTask = new Todo(description, false);
        tasks.add(addedTask);
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
        tasks.add(addedTask);
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
        tasks.add(addedTask);
        return addedTask;
    }

    /**
     * Adds the specified existing task to this task list.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
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

    /**
     * Deletes and returns the task at the specified one-based position.
     *
     * @param index One-based position of the task to delete.
     * @return Deleted task.
     * @throws InvalidTaskNumberException If the position is outside the task list.
     */
    public Task deleteTask(int index) throws InvalidTaskNumberException {
        if (index < 1 || index > getCount()) {
            // Reject deletion when the task number does not identify an existing task.
            throw new InvalidTaskNumberException(
                    "What do you want me to delete, your brain? The integer you gave is out of bounds.");
        }
        return tasks.remove(index - 1);
    }
}
