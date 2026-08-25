package yuno.action;

import yuno.exception.InvalidCommandFormatException;
import yuno.exception.YunoException;
import yuno.storage.Storage;
import yuno.task.TaskList;
import yuno.ui.Ui;

/**
 * Represents a command that removes every task from the task list.
 */
public class ClearAction extends Action {
    /**
     * Creates an action that clears the task list.
     *
     * @param commandArguments Additional command arguments, which must be blank.
     */
    public ClearAction(String commandArguments) {
        super(commandArguments);
    }

    /**
     * Clears the task list, saves the empty list, and displays a confirmation.
     *
     * @param taskList Task list to clear.
     * @param ui User interface used to display the confirmation.
     * @param storage Storage used to save the empty task list.
     * @return Always true.
     * @throws YunoException If additional command data is supplied or the empty task list cannot be saved.
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws YunoException {
        if (!getCommandArguments().isBlank()) {
            throw new InvalidCommandFormatException("Why are you entering irrelevant details?");
        }
        taskList.clearTasks();
        storage.save(taskList);
        ui.printTasksCleared();
        return true;
    }
}
