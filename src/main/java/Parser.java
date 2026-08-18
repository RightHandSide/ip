/**
 * Converts user input into actions that the chatbot can execute.
 */
public class Parser {
    /**
     * Parses the specified user input into an action.
     *
     * @param input User input to parse.
     * @return Action that represents the parsed input.
     */
    public Action parse(String input) {
        String command = input.split(" ")[0];
        switch (command) {
            case "list":
                return new ListAction(null);
            case "todo":
                return new TodoAction(input.substring(input.indexOf(" ") + 1));
            case "deadline":
                return new DeadlineAction(input.substring(input.indexOf(" ") + 1));
            case "event":
                return new EventAction(input.substring(input.indexOf(" ") + 1));
            case "mark":
                return new MarkAction(input.substring(input.indexOf(" ") + 1));
            case "unmark":
                return new UnmarkAction(input.substring(input.indexOf(" ") + 1));
            case "bye":
                return new ByeAction(null);
            default:
                return null;
        }
    }
}
