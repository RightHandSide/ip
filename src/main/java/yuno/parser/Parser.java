package yuno.parser;

import yuno.action.Action;
import yuno.action.ByeAction;
import yuno.action.DeadlineAction;
import yuno.action.DeleteAction;
import yuno.action.EventAction;
import yuno.action.ListAction;
import yuno.action.MarkAction;
import yuno.action.TodoAction;
import yuno.action.UnmarkAction;
import yuno.exception.UnknownCommandException;

/**
 * Converts user input into actions that the chatbot can execute.
 */
public class Parser {
    /**
     * Parses the specified user input into an action.
     *
     * @param input User input to parse.
     * @return Action that represents the parsed input.
     * @throws UnknownCommandException If the command is not recognized.
     */
    public Action parse(String input) throws UnknownCommandException {
        String strippedInput = input.strip();
        String command = strippedInput.split(" ")[0].strip();
        int separatorIndex = strippedInput.indexOf(" ");
        String description = separatorIndex == -1 ? "" : strippedInput.substring(separatorIndex + 1);
        switch (command) {
            case "list":
                return new ListAction(description);
            case "bye":
                return new ByeAction(description);
            case "todo":
                return new TodoAction(description);
            case "deadline":
                return new DeadlineAction(description);
            case "event":
                return new EventAction(description);
            case "mark":
                return new MarkAction(description);
            case "unmark":
                return new UnmarkAction(description);
            case "delete":
                return new DeleteAction(description);
            default:
                // Reject input whose command word is not supported by Yuno.
                throw new UnknownCommandException(
                        "Did you look at what you are typing? It's just a random string of command.");
        }
    }
}
