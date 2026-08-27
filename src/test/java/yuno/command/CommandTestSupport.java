package yuno.command;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;

import yuno.exception.FileStorageException;
import yuno.exception.InvalidTaskNumberException;
import yuno.storage.Storage;
import yuno.task.Task;
import yuno.task.TaskList;
import yuno.ui.Ui;

abstract class CommandTestSupport {
    @TempDir
    protected Path tempDir;
    protected TaskList taskList;
    protected RecordingUi ui;
    protected Storage storage;

    @BeforeEach
    void setUpCommandDependencies() throws FileStorageException {
        taskList = new TaskList();
        ui = new RecordingUi();
        storage = new Storage(tempDir.resolve("tasks.txt"));
    }

    static class RecordingUi extends Ui {
        Task addedTask;
        TaskList displayedTaskList;
        List<Task> displayedDateTasks;
        List<Task> displayedNameTasks;
        Task markedTask;
        Task unmarkedTask;
        Task deletedTask;
        boolean isByePrinted;
        boolean areTasksClearedPrinted;

        @Override
        public void printBye() {
            isByePrinted = true;
        }

        @Override
        public void printAddTask(Task task) {
            addedTask = task;
        }

        @Override
        public void printList(TaskList taskList) throws InvalidTaskNumberException {
            displayedTaskList = taskList;
        }

        @Override
        public void printTasksForDate(List<Task> matchingTasks) {
            displayedDateTasks = matchingTasks;
        }

        @Override
        public void printTasksForName(List<Task> matchingTasks) {
            displayedNameTasks = matchingTasks;
        }

        @Override
        public void printMarkTask(Task task) {
            markedTask = task;
        }

        @Override
        public void printUnmarkTask(Task task) {
            unmarkedTask = task;
        }

        @Override
        public void printDeleteTask(Task task) {
            deletedTask = task;
        }

        @Override
        public void printTasksCleared() {
            areTasksClearedPrinted = true;
        }
    }
}
