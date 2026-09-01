package yuno.ui;

import java.util.List;
import java.util.Scanner;

import yuno.exception.InvalidTaskNumberException;
import yuno.task.Task;
import yuno.task.TaskList;

/**
 * Reads commands from the user and displays chatbot messages and task information.
 */
public class Ui {
    /** Separates sections of chatbot output. */
    private static final String DIVIDER = "_".repeat(50);
    /** Reads commands entered through standard input. */
    private final Scanner scanner = new Scanner(System.in);
    /** Most recent response produced by Yuno. */
    private String latestResponse = "";

    /**
     * Creates a user interface that reads commands from standard input.
     */
    public Ui() {
    }

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
        latestResponse = banner + "\nI'm Yuno.\nCan we just get this over quickly?";
        System.out.println(latestResponse);
        System.out.println(DIVIDER);
    }

    /**
     * Displays the farewell message.
     */
    public void printBye() {
        latestResponse = "Finally! Bye. I'm leaving!";
        System.out.println(latestResponse);
        System.out.println(DIVIDER);
    }

    /**
     * Displays confirmation that the specified task was added.
     *
     * @param task Added task to display.
     */
    public void printAddTask(Task task) {
        latestResponse = String.format("Added:\n%s\nJust another task you won't finish.", task);
        System.out.println(latestResponse);
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
        latestResponse = output.toString();
        System.out.print(latestResponse);
        System.out.println(DIVIDER);
    }

    /**
     * Displays tasks that are relevant for a requested date.
     *
     * @param matchingTasks Tasks relevant for the requested date.
     */
    public void printTasksForDate(List<Task> matchingTasks) {
        StringBuilder output = new StringBuilder();
        if (matchingTasks.isEmpty()) {
            output.append(
                    "You have nothing. What I see is just someone being lazy on this particular date.\n");
        } else {
            output.append("You are not even capable to finish all these in one go.\n");
            output.append(formatTasks(matchingTasks));
        }
        latestResponse = output.toString();
        System.out.print(latestResponse);
        System.out.println(DIVIDER);
    }

    /**
     * Displays tasks whose descriptions contain requested text.
     *
     * @param matchingTasks Tasks with descriptions containing the requested text.
     */
    public void printTasksForName(List<Task> matchingTasks) {
        StringBuilder output = new StringBuilder();
        if (matchingTasks.isEmpty()) {
            output.append("You have nothing. You must be really happy since you are so lazy.\n");
        } else {
            output.append("So many task sharing a word. Could you be repeating task to feel accomplished?\n");
            output.append(formatTasks(matchingTasks));
        }
        latestResponse = output.toString();
        System.out.print(latestResponse);
        System.out.println(DIVIDER);
    }

    /**
     * Formats the specified tasks as a bulleted list.
     *
     * @param tasks Tasks to format.
     * @return Display-ready bulleted task list.
     */
    private String formatTasks(List<Task> tasks) {
        StringBuilder output = new StringBuilder();
        for (Task task : tasks) {
            output.append("- ").append(task).append("\n");
        }
        return output.toString();
    }

    /**
     * Displays confirmation that the specified task was marked as completed.
     *
     * @param task Task that was marked as completed.
     */
    public void printMarkTask(Task task) {
        latestResponse = "You actually completed a task? Bet it's the only task you'll ever complete.\n"
                + task;
        System.out.println(latestResponse);
        System.out.println(DIVIDER);
    }

    /**
     * Displays confirmation that the specified task was marked as incomplete.
     *
     * @param task Task that was marked as incomplete.
     */
    public void printUnmarkTask(Task task) {
        latestResponse = "Wow! So you lied about completing it? Typical behavior from you.\n"
                + task;
        System.out.println(latestResponse);
        System.out.println(DIVIDER);
    }

    /**
     * Displays confirmation that the specified task was deleted.
     *
     * @param task Deleted task to display.
     */
    public void printDeleteTask(Task task) {
        latestResponse = "Wow! Did you give up, or did you actually finish it?\n" + task;
        System.out.println(latestResponse);
        System.out.println(DIVIDER);
    }

    /**
     * Displays confirmation that every task was removed.
     */
    public void printTasksCleared() {
        latestResponse = "Finally. Now that everything is gone, can I go now?";
        System.out.println(latestResponse);
        System.out.println(DIVIDER);
    }

    /**
     * Displays the specified exception message.
     *
     * @param message Exception message to display.
     */
    public void printException(String message) {
        latestResponse = message;
        System.out.println(latestResponse);
        System.out.println(DIVIDER);
    }

    /**
     * Returns the next command entered by the user.
     *
     * @return Command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Returns the most recent response produced by Yuno.
     *
     * @return Most recent Yuno response.
     */
    public String getResponse() {
        return latestResponse;
    }
}
