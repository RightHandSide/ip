package yuno.action;

import yuno.task.TaskList;
import yuno.ui.Ui;

/**
 * Represents a command that marks a task as completed.
 */
public class MarkAction extends Action {
    /**
     * Creates an action with the index of a task to mark.
     *
     * @param taskDescription One-based task index from the user.
     */
    public MarkAction(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Marks the specified task as completed and displays its confirmation.
     *
     * @param taskList Task list that contains the task.
     * @param ui User interface used to display the confirmation.
     * @return Always true.
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui) {
        int index = Integer.parseInt(this.getTaskDescription());
        taskList.markTask(index);
        ui.printMarkTask(taskList.getTask(index));
        return true;
    }
}

