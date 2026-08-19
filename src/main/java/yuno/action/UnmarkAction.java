package yuno.action;

import yuno.exception.InvalidTaskNumberException;
import yuno.task.TaskList;
import yuno.ui.Ui;

/**
 * Represents a command that marks a task as incomplete.
 */
public class UnmarkAction extends Action {
    /**
     * Creates an action with the index of a task to unmark.
     *
     * @param taskDescription One-based task index from the user.
     */
    public UnmarkAction(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Marks the specified task as incomplete and displays its confirmation.
     *
     * @param taskList Task list that contains the task.
     * @param ui User interface used to display the confirmation.
     * @return Always true.
     * @throws InvalidTaskNumberException If the task number is not an integer or does not identify a task.
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui) throws InvalidTaskNumberException {
        try {
            int index = Integer.parseInt(getTaskDescription());
            taskList.unmarkTask(index);
            ui.printUnmarkTask(taskList.getTask(index));
            return true;
        } catch (NumberFormatException exception) {
            // Convert a missing or nonnumeric task number into a user-facing error.
            throw new InvalidTaskNumberException("Did you even give me an integer? Please don't waste my time!");
        }
    }
}
