package yuno.storage;

import yuno.exception.InvalidTaskNumberException;
import yuno.task.Task;
import yuno.task.TaskList;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Manages the file used to store Yuno tasks.
 */
public class Storage {
    /** Path of the file used to store task data. */
    private static final Path FILE_PATH = Path.of("./data/yuno.txt");

    /**
     * Initializes storage and attempts to create the task data file if it does not exist.
     */
    public Storage() {
        try {
            Files.createDirectories(FILE_PATH.getParent());

            if (Files.notExists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
            }
        } catch (IOException e) {

        }
    }

    /**
     * Attempts to rewrite the task data file with all tasks in the specified task list.
     *
     * @param taskList Task list to save.
     * @throws InvalidTaskNumberException If a task cannot be retrieved from the task list.
     */
    public void save(TaskList taskList) throws InvalidTaskNumberException {
        try (FileWriter fw = new FileWriter(FILE_PATH.toFile())) {
            for (int i = 0; i < taskList.getCount(); i++) {
                fw.write(taskList.getTask(i + 1).toStorageString());
                fw.write(System.lineSeparator());
            }
        } catch (IOException e) {

        }
    }
}
