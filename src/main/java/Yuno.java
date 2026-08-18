import java.util.Scanner;

public class Yuno {
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
            System.out.println(input);
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
}
