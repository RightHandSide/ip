public class Parser {
    public Action parse(String input) {
        String command = input.split(" ")[0];
        switch (command) {
            case "bye":
                return new Action(Action.Type.BYE, null);
            case "list":
                return new Action(Action.Type.LIST, null);
            default:
                return new Action(Action.Type.ADD, input);
        }
    }
}
