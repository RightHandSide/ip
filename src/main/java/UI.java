public class UI {
    private static String divider = "_".repeat(50);

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

    public void printBye() {
        System.out.println(divider);
        System.out.println("Finally! Bye, I'm going!");
        System.out.println(divider);
    }

    public void printAddTask(String task) {
        System.out.println(String.format("Added %s. Just another task you would not finish.", task));
        System.out.println(divider);
    }

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

    public void printMarkTask(Task task) {
        System.out.println("You actually completed a task? Bet it's the only task you would complete.");
        System.out.println("    " + task.toString());
        System.out.println(divider);
    }

    public void printUnmarkTask(Task task) {
        System.out.println("Wow! So you lied about completing it? Typical behavior.");
        System.out.println("    " + task.toString());
        System.out.println(divider);
    }
}