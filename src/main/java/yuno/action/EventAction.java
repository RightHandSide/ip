package yuno.action;

import yuno.exception.InvalidCommandFormatException;
import yuno.exception.YunoException;
import yuno.storage.Storage;
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
     * @param storage Storage used to save the updated task list.
     * @return Always true.
     * @throws YunoException If the task details are invalid or task data cannot be accessed.
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws YunoException {
        String taskDescription = getTaskDescription();
        String[] eventParts = taskDescription.split(" /from | /to ", 3);
        if (eventParts[0].isBlank()) {
            throw new InvalidCommandFormatException("If you have no task, please don't bother me.");
        } else if (eventParts.length < 3 || eventParts[1].isBlank() || eventParts[2].isBlank()) {
            throw new InvalidCommandFormatException(
                    "If your task does not have a start and end time, save me some time and use another task type.");
        } else if (taskDescription.indexOf("/from") > taskDescription.indexOf("/to")) {
            throw new InvalidCommandFormatException(
                    "Your order is wrong. Check it before wasting my time.");
        }
        Task event = taskList.addTask(eventParts[0], eventParts[1], eventParts[2]);
        storage.save(taskList);
        ui.printAddTask(event);
        return true;
    }
}
