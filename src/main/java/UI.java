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
        System.out.println("Hi! I'm Yuno.");
        System.out.println("Please let me do something for you!");
        System.out.println(divider);
    }

    public void printBye() {
        System.out.println(divider);
        System.out.println("Bye. See you next time!");
        System.out.println(divider);
    }

    public void printAddTask(String task) {
        System.out.println("added: " + task);
        System.out.println(divider);
    }

    public void printList(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.getCount(); i++) {
            sb.append(i + 1);
            sb.append(". " + taskList.getTask(i) + "\n");
        }
        System.out.print(sb);
        System.out.println(divider);
    }
}