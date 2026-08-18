public class TaskList {
    private Task[] taskList;
    private int taskCount;

    public TaskList() {
        this.taskList = new Task[100];
        this.taskCount = 0;
    }

    public int getCount() {
        return this.taskCount;
    }

    public Task getTask(int index) {
        return this.taskList[index - 1];
    }

    public void addTask(String task) {
        this.taskList[this.taskCount] = new Task(task, false);
        this.taskCount += 1;
    }

    public void markTask(int index) {
        this.getTask(index).markDone();
    }

    public void unmarkTask(int index) {
        this.getTask(index).unmarkDone();
    }
}