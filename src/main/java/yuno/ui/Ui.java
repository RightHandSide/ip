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
        String banner = "__   __ _   _ _   _  ___\n"
                + "\\ \\ / /| | | | \\ | |/ _ \\\n"
                + " \\ V / | | | |  \\| | | | |\n"
                + "  | |  | |_| | |\\  | |_| |\n"
                + "  |_|   \\___/|_| \\_|\\___/\n";
        System.out.println(DIVIDER); // Open the greeting section.
        System.out.println(banner); // Display the chatbot banner.
        System.out.println("I'm Yuno."); // Introduce the chatbot.
        System.out.println("Can we just get this over quickly?"); // Prompt the user for a command.
        System.out.println(DIVIDER); // Close the greeting section.
    }

    /**
     * Displays the farewell message.
     */
    public void printBye() {
        System.out.println("Finally! Bye. I'm leaving!"); // Display the farewell message.
        System.out.println(DIVIDER); // Close the farewell section.
    }

    /**
     * Displays confirmation that the specified task was added.
     *
     * @param task Added task to display.
     */
    public void printAddTask(Task task) {
        System.out.println(String.format(
                "Added:\n%s\nJust another task you won't finish.", task)); // Confirm the added task.
        System.out.println(DIVIDER); // Close the task-addition section.
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
        System.out.print(output); // Display the numbered task list.
        System.out.println(DIVIDER); // Close the task-list section.
    }

    /**
     * Displays confirmation that the specified task was marked as completed.
     *
     * @param task Task that was marked as completed.
     */
    public void printMarkTask(Task task) {
        System.out.println(
                "You actually completed a task? Bet it's the only task you'll ever complete."); // Confirm marking.
        System.out.println(task); // Display the updated task.
        System.out.println(DIVIDER); // Close the mark-confirmation section.
    }

    /**
     * Displays confirmation that the specified task was marked as incomplete.
     *
     * @param task Task that was marked as incomplete.
     */
    public void printUnmarkTask(Task task) {
        System.out.println("Wow! So you lied about completing it? Typical behavior."); // Confirm unmarking.
        System.out.println(task); // Display the updated task.
        System.out.println(DIVIDER); // Close the unmark-confirmation section.
    }

    /**
     * Displays confirmation that the specified task was deleted.
     *
     * @param task Deleted task to display.
     */
    public void printDeleteTask(Task task) {
        System.out.println("Wow! Did you give up, or did you actually finish it?"); // Confirm deletion.
        System.out.println(task); // Display the deleted task.
        System.out.println(DIVIDER); // Close the delete-confirmation section.
    }

    /**
     * Displays the specified exception message.
     *
     * @param message Exception message to display.
     */
    public void printException(String message) {
        System.out.println(message); // Display the user-facing exception message.
        System.out.println(DIVIDER); // Close the exception section.
    }
}
