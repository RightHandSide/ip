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
import yuno.exception.YunoException;

/**
 * Converts user input into actions that the chatbot can execute.
 */
public class Parser {
    /**
     * Represents a command recognized by Yuno.
     */
    private enum CommandType {
        LIST("list"),
        BYE("bye"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete");

        /** Command keyword entered by the user. */
        private final String command;

        CommandType(String command) {
            this.command = command;
        }

        /**
         * Returns the command type matching the specified command keyword.
         *
         * @param keyword Command keyword entered by the user.
         * @return Command type matching the keyword.
         * @throws UnknownCommandException If the command keyword is not recognized.
         */
        public static CommandType from(String keyword) throws UnknownCommandException {
            for (CommandType commandType : values()) {
                if (commandType.command.equals(keyword)) {
                    return commandType;
                }
            }
            throw new UnknownCommandException(
                    "Did you look at what you typed? That's just a random command.");
        }
    }

    /**
     * Parses the specified user input into an action.
     *
     * @param input User input to parse.
     * @return Action that represents the parsed input.
     * @throws YunoException If the command is not recognized or cannot be parsed.
     */
    public Action parse(String input) throws YunoException {
        String strippedInput = input.strip();
        CommandType command = CommandType.from(strippedInput.split(" ")[0].strip());
        int separatorIndex = strippedInput.indexOf(" ");
        String description = separatorIndex == -1 ? "" : strippedInput.substring(separatorIndex + 1);
        return switch (command) {
            case LIST -> new ListAction(description);
            case BYE -> new ByeAction(description);
            case TODO -> new TodoAction(description);
            case DEADLINE -> new DeadlineAction(description);
            case EVENT -> new EventAction(description);
            case MARK -> new MarkAction(description);
            case UNMARK -> new UnmarkAction(description);
            case DELETE -> new DeleteAction(description);
        };
    }
}
