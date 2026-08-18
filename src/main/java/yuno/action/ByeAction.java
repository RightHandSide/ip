package yuno.action;

import yuno.task.TaskList;
import yuno.ui.Ui;

/**
 * Represents a command that displays a farewell and ends the chatbot session.
 */
public class ByeAction extends Action {
    /**
     * Creates an action that ends the chatbot session.
     *
     * @param taskDescription Unused command data.
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
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui) {
        ui.printBye();
        return false;
    }
}
