public class MarkAction extends Action {
    public MarkAction(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public boolean execute(TaskList taskList, UI ui) {
        int index = Integer.parseInt(this.getTaskDescription());
        taskList.markTask(index);
        ui.printMarkTask(taskList.getTask(index));
        return true;
    }
}

