import java.util.Scanner;

public class Yuno {
    public static void main(String[] args) {
        UI ui = new UI();
        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;

        ui.printGreeting();
        while (isRunning) {
            String input = scanner.nextLine();
            Action action = parser.parse(input);
            isRunning = action.execute(taskList, ui);
        }
    }
}
