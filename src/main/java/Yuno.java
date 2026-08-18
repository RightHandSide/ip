import java.util.Scanner;

public class Yuno {
    private static String[] taskList = new String[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String banner = "__   __                 \n"
                + "\\ \\ / /   _ _ __   ___ \n"
                + " \\ V / | | | '_ \\ / _ \\\n"
                + "  | | | |_| | | | | (_) |\n"
                + "  |_|  \\__,_|_| |_|\\___/\n";
        String divider = "____________________________________________________________";
        System.out.println(divider);
        System.out.println(banner);
        System.out.println("Hello! I'm Yuno.");
        System.out.println("What can I do for you?");

        System.out.println(divider);
        String input = scanner.nextLine();

        while (!isBye(input)) {
            System.out.println(divider);
            if (isList(input)) {
                // If Input is "list", Make List
                System.out.print(makeList());
            } else {
                // if Input is not "list", Add to List
                addList(input);
                System.out.println("added: " + input);
            }
            System.out.println(divider);
            input = scanner.nextLine();
        }

        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
    private static boolean isBye(String input) {
        return input.equals("bye");
    }

    private static boolean isList(String input) {
        return input.equals("list");
    }

    private static void addList(String task) {
        taskList[taskCount] = task;
        taskCount += 1;
    }

    private static String makeList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskCount; i++) {
            sb.append(i + 1);
            sb.append(". " + taskList[i] + "\n");
        }
        return sb.toString();
    }
}
