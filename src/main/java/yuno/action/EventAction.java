package yuno.action;

import yuno.task.Task;
import yuno.task.TaskList;
import yuno.ui.Ui;

/**
 * Represents a command that adds an event task.
 */
public class EventAction extends Action {
    /**
     * Creates an action with the description, start time, and end time of an event.
     *
     * @param taskDescription Description and time text from the user.
     */
    public EventAction(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Adds an event task and displays its confirmation.
     *
     * @param taskList Task list to modify.
     * @param ui User interface used to display the confirmation.
     * @return Always true.
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui) {
        String[] eventDesc = this.getTaskDescription().split(" /from | /to ", 3);
        Task event = taskList.addTask(eventDesc[0], eventDesc[1], eventDesc[2]);
        ui.printAddTask(event);
        return true;
    }
}
