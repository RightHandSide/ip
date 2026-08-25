package yuno.parser;

import yuno.action.Action;
import yuno.action.ByeAction;
import yuno.action.ClearAction;
import yuno.action.DeadlineAction;
import yuno.action.DeleteAction;
import yuno.action.EventAction;
import yuno.action.FindByDateAction;
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
     * Represents a command recognized by Yuno.
     */
    private enum CommandType {
        CLEAR("clear"),
        LIST("list"),
        FIND_BY_DATE("date"),
        BYE("bye"),

        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),

        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete");

        /** Command keyword entered by the user. */
        private final String keyword;

        CommandType(String keyword) {
            this.keyword = keyword;
        }

        /**
         * Returns the command type matching the specified command keyword.
         *
         * @param keyword Command keyword entered by the user.
         * @return Command type matching the keyword.
         * @throws UnknownCommandException If the command keyword is not recognized.
         */
        private static CommandType from(String keyword) throws UnknownCommandException {
            for (CommandType commandType : values()) {
                if (commandType.keyword.equals(keyword)) {
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
     * @throws UnknownCommandException If the command is not recognized or cannot be parsed.
     */
    public Action parse(String input) throws UnknownCommandException {
        String strippedInput = input.strip();
        CommandType commandType = CommandType.from(strippedInput.split(" ")[0].strip());
        int separatorIndex = strippedInput.indexOf(" ");
        String commandArguments = separatorIndex == -1 ? "" : strippedInput.substring(separatorIndex + 1);
        return switch (commandType) {
            case CLEAR -> new ClearAction(commandArguments);
            case LIST -> new ListAction(commandArguments);
            case FIND_BY_DATE -> new FindByDateAction(commandArguments);
            case BYE -> new ByeAction(commandArguments);
            case TODO -> new TodoAction(commandArguments);
            case DEADLINE -> new DeadlineAction(commandArguments);
            case EVENT -> new EventAction(commandArguments);
            case MARK -> new MarkAction(commandArguments);
            case UNMARK -> new UnmarkAction(commandArguments);
            case DELETE -> new DeleteAction(commandArguments);
        };
    }
}
