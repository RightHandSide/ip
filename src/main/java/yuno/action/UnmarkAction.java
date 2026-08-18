package yuno.action;

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
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui) {
        int index = Integer.parseInt(this.getTaskDescription());
        taskList.unmarkTask(index);
        ui.printUnmarkTask(taskList.getTask(index));
        return true;
    }
}
