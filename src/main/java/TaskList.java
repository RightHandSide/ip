public class TaskList {
    private String[] taskList;
    private int taskCount;

    public TaskList() {
        this.taskList = new String[100];
        this.taskCount = 0;
    }

    public int getCount() {
        return this.taskCount;
    }

    public String getTask(int index) {
        return this.taskList[index];
    }

    public void addTask(String task) {
        this.taskList[this.taskCount] = task;
        this.taskCount += 1;
    }
}