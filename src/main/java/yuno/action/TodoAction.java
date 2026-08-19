package yuno.action;

import yuno.exception.InvalidCommandFormatException;
import yuno.task.Task;
import yuno.task.TaskList;
import yuno.ui.Ui;

/**
 * Represents a command that adds a to-do task.
 */
public class TodoAction extends Action {
    /**
     * Creates an action with the description of a to-do task to add.
     *
     * @param taskDescription Description of the task to add.
     */
    public TodoAction(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Adds a to-do task and displays its confirmation.
     *
     * @param taskList Task list to modify.
     * @param ui User interface used to display the confirmation.
     * @return Always true.
     * @throws InvalidCommandFormatException If the task description is blank.
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui) throws InvalidCommandFormatException {
        if (getTaskDescription().isBlank()) {
            throw new InvalidCommandFormatException("If you have no task, please don't bother me.");
        }
        Task todo = taskList.addTask(getTaskDescription());
        ui.printAddTask(todo);
        return true;
    }
}
