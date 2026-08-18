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
            case "bye":
                return new Action(Action.Type.BYE, null);
            case "list":
                return new Action(Action.Type.LIST, null);
            case "mark":
                return new Action(Action.Type.MARK, input.substring(input.indexOf(" ") + 1));
            case "unmark":
                return new Action(Action.Type.UNMARK, input.substring(input.indexOf(" ") + 1));
            default:
                return new Action(Action.Type.ADD, input);
        }
    }
}
