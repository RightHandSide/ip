package yuno;

import java.util.Scanner;

import yuno.action.Action;
import yuno.exception.FileStorageException;
import yuno.exception.YunoException;
import yuno.parser.Parser;
import yuno.storage.Storage;
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
        Storage storage;
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);

        ui.printGreeting();
        try {
            storage = new Storage();
            storage.load(taskList);
        } catch (FileStorageException exception) {
            ui.printException(exception.getMessage());
            return;
        }

        boolean isRunning = true;

        while (isRunning) {
            try {
                String input = scanner.nextLine();
                Action action = parser.parse(input);
                isRunning = action.execute(taskList, ui, storage);
            } catch (YunoException exception) {
                ui.printException(exception.getMessage());
                isRunning = true;
            }
        }
    }
}
