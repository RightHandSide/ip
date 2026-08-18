public class Action {
    public enum Type {
        LIST, ADD, MARK, UNMARK, BYE
    }

    private final Type type;
    private final String taskDescription;

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
