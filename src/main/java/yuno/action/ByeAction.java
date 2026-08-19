package yuno.action;

import yuno.exception.InvalidCommandFormatException;
import yuno.task.TaskList;
import yuno.ui.Ui;

/**
 * Represents a command that displays a farewell and ends the chatbot session.
 */
public class ByeAction extends Action {
    /**
     * Creates an action that ends the chatbot session.
     *
     * @param taskDescription Additional command data, which must be blank.
     */
    public ByeAction(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Displays a farewell and ends the chatbot session.
     *
     * @param taskList Task list that is not modified.
     * @param ui User interface used to display the farewell.
     * @return Always false.
     * @throws InvalidCommandFormatException If additional command data is supplied.
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui) throws InvalidCommandFormatException {
        if (!getTaskDescription().isBlank()) {
            throw new InvalidCommandFormatException("Please don't enter irrelevant details.");
        }
        ui.printBye();
        return false;
    }
}
