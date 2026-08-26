package yuno;

import yuno.command.Command;
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
    /** User interface used to read commands and display messages. */
    private final Ui ui;
    /** Parser used to convert user input into executable commands. */
    private final Parser parser;
    /** Storage used to load and save tasks. */
    private final Storage storage;
    /** Task list managed during the chatbot session. */
    private final TaskList taskList;

    /**
     * Creates a chatbot using the specified user interface and loads saved tasks.
     *
     * @param ui User interface used for the chatbot session.
     * @throws FileStorageException If storage cannot be initialized or saved tasks cannot be loaded.
     */
    private Yuno(Ui ui) throws FileStorageException {
        this(ui, new Parser(), new Storage(), new TaskList());
        storage.load(taskList);
    }

    /**
     * Creates a chatbot with the specified collaborators.
     * This constructor supports isolated testing without changing normal startup behavior.
     *
     * @param ui User interface used for the chatbot session.
     * @param parser Parser used to interpret commands.
     * @param storage Storage used to load and save tasks.
     * @param taskList Task list managed during the session.
     */
    Yuno(Ui ui, Parser parser, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.parser = parser;
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Runs the command-processing loop until a command ends the session.
     */
    void run() {
        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = ui.nextCommand();
                Command command = parser.parse(input);
                isRunning = command.execute(taskList, ui, storage);
            } catch (YunoException exception) {
                ui.printException(exception.getMessage());
            }
        }
    }

    /**
     * Starts the chatbot and runs it until a command ends the session or storage initialization fails.
     *
     * @param args Command-line arguments, which are not used.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.printGreeting();
        try {
            new Yuno(ui).run();
        } catch (FileStorageException exception) {
            ui.printException(exception.getMessage());
        }
    }
}
