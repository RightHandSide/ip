package yuno.ui;

import yuno.exception.InvalidTaskNumberException;
import yuno.task.Task;
import yuno.task.TaskList;

/**
 * Displays chatbot messages and task information to the user.
 */
public class Ui {
    /** Separates sections of chatbot output. */
    private static final String DIVIDER = "_".repeat(50);

    /**
     * Displays the chatbot banner and greeting.
     */
    public void printGreeting() {
        String banner = """
                __   __ _   _ _   _  ___
                \\ \\ / /| | | | \\ | |/ _ \\
                 \\ V / | | | |  \\| | | | |
                  | |  | |_| | |\\  | |_| |
                  |_|   \\___/|_| \\_|\\___/
                """;
        System.out.println(DIVIDER);
        System.out.println(banner);
        System.out.println("I'm Yuno.");
        System.out.println("Can we just get this over quickly?");
        System.out.println(DIVIDER);
    }

    /**
     * Displays the farewell message.
     */
    public void printBye() {
        System.out.println("Finally! Bye. I'm leaving!");
        System.out.println(DIVIDER);
    }

    /**
     * Displays confirmation that the specified task was added.
     *
     * @param task Added task to display.
     */
    public void printAddTask(Task task) {
        System.out.printf("Added:\n%s\nJust another task you won't finish.%n", task);
        System.out.println(DIVIDER);
    }

    /**
     * Displays every task in the specified task list.
     *
     * @param taskList Task list to display.
     * @throws InvalidTaskNumberException If a task cannot be retrieved from the task list.
     */
    public void printList(TaskList taskList) throws InvalidTaskNumberException {
        StringBuilder output = new StringBuilder();
        if (taskList.getCount() == 0) {
            output.append("Wow, not even a single task? You are so lazy.\n");
        } else {
            output.append("Wow. Look at how slow you are at completing these tasks.\n");
            for (int i = 0; i < taskList.getCount(); i++) {
                output.append(i + 1);
                output.append(". ").append(taskList.getTask(i + 1)).append("\n");
            }
        }
        System.out.print(output);
        System.out.println(DIVIDER);
    }

    /**
     * Displays confirmation that the specified task was marked as completed.
     *
     * @param task Task that was marked as completed.
     */
    public void printMarkTask(Task task) {
        System.out.println(
                "You actually completed a task? Bet it's the only task you'll ever complete.");
        System.out.println(task);
        System.out.println(DIVIDER);
    }

    /**
     * Displays confirmation that the specified task was marked as incomplete.
     *
     * @param task Task that was marked as incomplete.
     */
    public void printUnmarkTask(Task task) {
        System.out.println("Wow! So you lied about completing it? Typical behavior from you.");
        System.out.println(task);
        System.out.println(DIVIDER);
    }

    /**
     * Displays confirmation that the specified task was deleted.
     *
     * @param task Deleted task to display.
     */
    public void printDeleteTask(Task task) {
        System.out.println("Wow! Did you give up, or did you actually finish it?");
        System.out.println(task);
        System.out.println(DIVIDER);
    }

    /**
     * Displays the specified exception message.
     *
     * @param message Exception message to display.
     */
    public void printException(String message) {
        System.out.println(message);
        System.out.println(DIVIDER);
    }
}
