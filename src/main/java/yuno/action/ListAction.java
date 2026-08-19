package yuno.action;

import yuno.exception.InvalidCommandFormatException;
import yuno.exception.YunoException;
import yuno.task.TaskList;
import yuno.ui.Ui;

/**
 * Represents a command that displays all stored tasks.
 */
public class ListAction extends Action {
    /**
     * Creates an action that displays the task list.
     *
     * @param taskDescription Additional command data, which must be blank.
     */
    public ListAction(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param taskList Task list to display.
     * @param ui User interface used to display the tasks.
     * @return Always true.
     * @throws YunoException If additional command data is supplied or a task cannot be retrieved.
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui) throws YunoException {
        if (!getTaskDescription().isBlank()) {
            // Reject extra arguments because the list command does not accept any.
            throw new InvalidCommandFormatException("Please don't enter irrelevant details.");
        }
        ui.printList(taskList);
        return true;
    }
}
