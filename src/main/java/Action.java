public class Action {
    public enum Type {
        BYE, LIST, ADD
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
        switch (this.getType()) {
            case BYE:
                ui.printBye();
                return false;
            case LIST:
                ui.printList(taskList);
                return true;
            case ADD:
                taskList.addTask(this.getTaskDescription());
                ui.printAddTask(this.getTaskDescription());
                return true;
            default:
                return false;
        }
    }
}
