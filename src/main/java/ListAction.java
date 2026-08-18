public class ListAction extends Action {
    public ListAction(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public boolean execute(TaskList taskList, UI ui) {
        ui.printList(taskList);
        return true;
    }
}
