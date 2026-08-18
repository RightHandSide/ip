package yuno.action;

import yuno.task.TaskList;
import yuno.ui.Ui;

/**
 * Represents a command that displays all stored tasks.
 */
public class ListAction extends Action {
    /**
     * Creates an action that displays the task list.
     *
     * @param taskDescription Unused command data.
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
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui) {
        ui.printList(taskList);
        return true;
    }
}
