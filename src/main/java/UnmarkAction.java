public class UnmarkAction extends Action {
    public UnmarkAction(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public boolean execute(TaskList taskList, UI ui) {
        int index = Integer.parseInt(this.getTaskDescription());
        taskList.unmarkTask(index);
        ui.printUnmarkTask(taskList.getTask(index));
        return true;
    }
}
