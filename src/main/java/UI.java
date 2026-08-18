/**
 * Displays chatbot messages and task information to the user.
 */
public class UI {
    /** Separates sections of chatbot output. */
    private static String divider = "_".repeat(50);

    /**
     * Displays the chatbot banner and greeting.
     */
    public void printGreeting() {
        String banner = "__   __                 \n"
                + "\\ \\ / /   _ _ __   ___ \n"
                + " \\ V / | | | '_ \\ / _ \\\n"
                + "  | |  | |_| | | | | (_) |\n"
                + "  |_|   \\__,_|_| |_|\\___/\n";
        System.out.println(divider);
        System.out.println(banner);
        System.out.println("I'm Yuno.");
        System.out.println("Can we just get this over quickly?");
        System.out.println(divider);
    }

    /**
     * Displays the farewell message.
     */
    public void printBye() {
        System.out.println("Finally! Bye, I'm going!");
        System.out.println(divider);
    }

    /**
     * Displays confirmation that the specified task was added.
     *
     * @param task Description of the added task.
     */
    public void printAddTask(Task task) {
        System.out.println(String.format("Added: \n%s\nJust another task you would not finish.", task));
        System.out.println(divider);
    }

    /**
     * Displays every task in the specified task list.
     *
     * @param taskList Task list to display.
     */
    public void printList(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Wow! Look at how slow you are at completing these tasks.\n");
        for (int i = 0; i < taskList.getCount(); i++) {
            sb.append(i + 1);
            sb.append(". " + taskList.getTask(i + 1).toString() + "\n");
        }
        System.out.print(sb);
        System.out.println(divider);
    }

    /**
     * Displays confirmation that the specified task was marked as completed.
     *
     * @param task Task that was marked as completed.
     */
    public void printMarkTask(Task task) {
        System.out.println("You actually completed a task? Bet it's the only task you would complete.");
        System.out.println(task.toString());
        System.out.println(divider);
    }

    /**
     * Displays confirmation that the specified task was marked as incomplete.
     *
     * @param task Task that was marked as incomplete.
     */
    public void printUnmarkTask(Task task) {
        System.out.println("Wow! So you lied about completing it? Typical behavior.");
        System.out.println(task.toString());
        System.out.println(divider);
    }
}
