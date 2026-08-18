package yuno.action;

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
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui) {
        String[] deadlineDesc = this.getTaskDescription().split(" /by ", 2);
        Task deadline = taskList.addTask(deadlineDesc[0], deadlineDesc[1]);
        ui.printAddTask(deadline);
        return true;
    }
}
