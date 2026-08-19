package yuno.action;

import yuno.exception.InvalidCommandFormatException;
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
     * @throws InvalidCommandFormatException If the description or event times are missing or incorrectly ordered.
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui) throws InvalidCommandFormatException {
        String taskDescription = getTaskDescription();
        String[] eventParts = taskDescription.split(" /from | /to ", 3);
        if (eventParts[0].isBlank()) {
            throw new InvalidCommandFormatException("If you have no task, please don't bother me.");
        } else if (eventParts.length < 3 || eventParts[1].isBlank() || eventParts[2].isBlank()) {
            throw new InvalidCommandFormatException(
                    "If your task does not have a start and end time, use another task type.");
        } else if (taskDescription.indexOf("/from") > taskDescription.indexOf("/to")) {
            throw new InvalidCommandFormatException(
                    "Your order is wrong. Check it before wasting my time.");
        }
        Task event = taskList.addTask(eventParts[0], eventParts[1], eventParts[2]);
        ui.printAddTask(event);
        return true;
    }
}
