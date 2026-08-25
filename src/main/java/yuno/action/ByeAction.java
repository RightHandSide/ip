package yuno.action;

import yuno.exception.InvalidCommandFormatException;
import yuno.storage.Storage;
import yuno.task.TaskList;
import yuno.ui.Ui;

/**
 * Represents a command that displays a farewell and ends the chatbot session.
 */
public class ByeAction extends Action {
    /**
     * Creates an action that ends the chatbot session.
     *
     * @param commandArguments Additional command arguments, which must be blank.
     */
    public ByeAction(String commandArguments) {
        super(commandArguments);
    }

    /**
     * Displays a farewell and ends the chatbot session.
     *
     * @param taskList Task list that is not modified.
     * @param ui User interface used to display the farewell.
     * @param storage Storage that is not modified.
     * @return Always false.
     * @throws InvalidCommandFormatException If additional command data is supplied.
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws InvalidCommandFormatException {
        if (!getCommandArguments().isBlank()) {
            throw new InvalidCommandFormatException("Why are you entering irrelevant details?");
        }
        ui.printBye();
        return false;
    }
}
