/**
 * Represents a user command and executes its associated behavior.
 */
public class Action {
    /**
     * Lists the command types supported by the chatbot.
     */
    public enum Type {
        LIST, ADD, MARK, UNMARK, BYE
    }

    /** Stores the type of behavior this action performs. */
    private final Type type;
    /** Stores the task description or task index supplied with this action. */
    private final String taskDescription;

    /**
     * Creates an action with the specified type and command data.
     *
     * @param type Type of behavior to perform.
     * @param taskDescription Task description or task index used by the action.
     */
    public Action(Type type, String taskDescription) {
        this.type = type;
        this.taskDescription = taskDescription;
    }

    public Type getType() {
        return this.type;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Executes this action using the specified task list and user interface.
     *
     * @param taskList Task list to read from or modify.
     * @param ui User interface used to display results.
     * @return False if this action ends the program; otherwise, true.
     */
    public boolean execute(TaskList taskList, UI ui) {
        int index;

        switch (this.getType()) {
            case LIST:
                ui.printList(taskList);
                return true;
            case ADD:
                taskList.addTask(this.getTaskDescription());
                ui.printAddTask(this.getTaskDescription());
                return true;
            case MARK:
                index = Integer.parseInt(this.getTaskDescription());
                taskList.markTask(index);
                ui.printMarkTask(taskList.getTask(index));
                return true;
            case UNMARK:
                index = Integer.parseInt(this.getTaskDescription());
                taskList.unmarkTask(index);
                ui.printUnmarkTask(taskList.getTask(index));
                return true;
            case BYE:
                ui.printBye();
                return false;
            default:
                return false;
        }
    }
}
