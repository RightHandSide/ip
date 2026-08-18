package yuno;

import java.util.Scanner;

import yuno.action.Action;
import yuno.parser.Parser;
import yuno.task.TaskList;
import yuno.ui.Ui;

/**
 * Starts the Yuno chatbot and coordinates user input with command execution.
 */
public class Yuno {
    /**
     * Runs the chatbot until the user enters a command that ends the session.
     *
     * @param args Command-line arguments, which are not used.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
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
