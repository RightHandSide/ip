public class TodoAction extends Action {
    public TodoAction(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public boolean execute(TaskList taskList, UI ui) {
        Task todo = taskList.addTask(this.getTaskDescription());
        ui.printAddTask(todo);
        return true;
    }
}
