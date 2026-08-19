package yuno.action;

import yuno.exception.InvalidCommandFormatException;
import yuno.task.Task;
import yuno.task.TaskList;
import yuno.ui.Ui;

/**
 * Represents a command that adds a deadline task.
 */
public class DeadlineAction extends Action {
    /**
     * Creates an action with the description and deadline of a task to add.
     *
     * @param taskDescription Description and deadline text from the user.
     */
    public DeadlineAction(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Adds a deadline task and displays its confirmation.
     *
     * @param taskList Task list to modify.
     * @param ui User interface used to display the confirmation.
     * @return Always true.
     * @throws InvalidCommandFormatException If the task description or deadline is missing.
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui) throws InvalidCommandFormatException {
        String[] deadlineParts = getTaskDescription().split(" /by ", 2);
        if (deadlineParts[0].isBlank()) {
            throw new InvalidCommandFormatException("If you have no task, please don't bother me.");
        } else if (deadlineParts.length < 2 || deadlineParts[1].isBlank()) {
            throw new InvalidCommandFormatException(
                    "If you are not constrained by a date, use another task type.");
        }
        Task deadline = taskList.addTask(deadlineParts[0], deadlineParts[1]);
        ui.printAddTask(deadline);
        return true;
    }
}
