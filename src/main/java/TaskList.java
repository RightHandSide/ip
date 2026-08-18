/**
 * Stores and manages the tasks entered during one chatbot session.
 */
public class TaskList {
    /** Stores the tasks in the order they were added. */
    private Task[] taskList;
    /** Records the number of tasks currently stored. */
    private int taskCount;

    /**
     * Creates an empty task list with space for up to 100 tasks.
     */
    public TaskList() {
        this.taskList = new Task[100];
        this.taskCount = 0;
    }

    public int getCount() {
        return this.taskCount;
    }

    /**
     * Returns the task at the specified one-based position.
     *
     * @param index One-based position of the task.
     * @return Task at the specified position.
     */
    public Task getTask(int index) {
        return this.taskList[index - 1];
    }

    /**
     * Adds a new incomplete task with the specified description.
     *
     * @param task Description of the task to add.
     */
    public Task addTask(String task) {
        Todo addedTask = new Todo(task, false);
        this.taskList[this.taskCount] = addedTask;
        this.taskCount += 1;
        return addedTask;
    }

    public Task addTask(String task, String deadline) {
        Deadline addedTask = new Deadline(task, false, deadline);
        this.taskList[this.taskCount] = addedTask;
        this.taskCount += 1;
        return addedTask;
    }

    public Task addTask(String task, String start, String end) {
        Event addedTask = new Event(task, false, start, end);
        this.taskList[this.taskCount] = addedTask;
        this.taskCount += 1;
        return addedTask;
    }

    /**
     * Marks the task at the specified one-based position as completed.
     *
     * @param index One-based position of the task to mark.
     */
    public void markTask(int index) {
        this.getTask(index).markDone();
    }

    /**
     * Marks the task at the specified one-based position as incomplete.
     *
     * @param index One-based position of the task to unmark.
     */
    public void unmarkTask(int index) {
        this.getTask(index).unmarkDone();
    }
}
