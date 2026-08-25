package yuno.action;

import yuno.exception.InvalidTaskNumberException;
import yuno.exception.YunoException;
import yuno.storage.Storage;
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
     * @param storage Storage used to save the updated task list.
     * @return Always true.
     * @throws YunoException If the task number is invalid or the updated task list cannot be saved.
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws YunoException {
        try {
            int index = Integer.parseInt(getTaskDescription());
            taskList.unmarkTask(index);
            storage.save(taskList);
            ui.printUnmarkTask(taskList.getTask(index));
            return true;
        } catch (NumberFormatException exception) {
            throw new InvalidTaskNumberException("Did you even give me an integer? Please don't waste my time!");
        }
    }
}
