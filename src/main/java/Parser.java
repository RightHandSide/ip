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
                return new Action(Action.Type.LIST, null);

            case "todo":
                return new Action(Action.Type.TODO, input.substring(input.indexOf(" ") + 1));
            case "deadline":
                return new Action(Action.Type.DEADLINE, input.substring(input.indexOf(" ") + 1));
            case "event":
                return new Action(Action.Type.EVENT, input.substring(input.indexOf(" ") + 1));

            case "mark":
                return new Action(Action.Type.MARK, input.substring(input.indexOf(" ") + 1));
            case "unmark":
                return new Action(Action.Type.UNMARK, input.substring(input.indexOf(" ") + 1));

            case "bye":
                return new Action(Action.Type.BYE, null);
            default:
                return null;
        }
    }
}
