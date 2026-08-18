public class ByeAction extends Action {
    public ByeAction(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public boolean execute(TaskList taskList, UI ui) {
        ui.printBye();
        return false;
    }
}
