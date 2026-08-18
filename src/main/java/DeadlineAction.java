public class DeadlineAction extends Action {
    public DeadlineAction(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public boolean execute(TaskList taskList, UI ui) {
        String[] deadlineDesc = this.getTaskDescription().split(" /by ", 2);
        Task deadline = taskList.addTask(deadlineDesc[0], deadlineDesc[1]);
        ui.printAddTask(deadline);
        return true;
    }
}
