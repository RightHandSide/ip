public class Parser {
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
