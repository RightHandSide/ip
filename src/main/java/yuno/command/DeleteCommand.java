package yuno.command;

import yuno.exception.InvalidTaskNumberException;
import yuno.exception.YunoException;
import yuno.storage.Storage;
import yuno.task.Task;
import yuno.task.TaskList;
import yuno.ui.Ui;

/**
 * Represents a command that deletes a task.
 */
public class DeleteCommand extends Command {
    /**
     * Creates a command with the index of a task to delete.
     *
     * @param taskNumber One-based task number entered by the user.
     */
    public DeleteCommand(String taskNumber) {
        super(taskNumber);
    }

    /**
     * Deletes the specified task and displays its confirmation.
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
            int taskNumber = Integer.parseInt(getCommandArguments());
            Task deletedTask = taskList.deleteTask(taskNumber);
            storage.save(taskList);
            ui.printDeleteTask(deletedTask);
            return true;
        } catch (NumberFormatException exception) {
            throw new InvalidTaskNumberException(
                    "Did you even give me an integer? Please don't waste my time!");
        }
    }
}
