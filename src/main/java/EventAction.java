public class EventAction extends Action {
    public EventAction(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public boolean execute(TaskList taskList, UI ui) {
        String[] eventDesc = this.getTaskDescription().split(" /from | /to ", 3);
        Task event = taskList.addTask(eventDesc[0], eventDesc[1], eventDesc[2]);
        ui.printAddTask(event);
        return true;
    }
}
