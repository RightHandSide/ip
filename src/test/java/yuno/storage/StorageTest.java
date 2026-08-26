package yuno.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import yuno.exception.FileStorageException;
import yuno.exception.InvalidTaskNumberException;
import yuno.task.Deadline;
import yuno.task.Event;
import yuno.task.TaskList;
import yuno.task.Todo;

class StorageTest {
    @TempDir
    private Path tempDir;

    @Test
    void constructor_missingParentAndFile_createsStorageFile() throws FileStorageException {
        Path filePath = tempDir.resolve("nested/data/tasks.txt");

        new Storage(filePath);

        assertTrue(Files.isRegularFile(filePath));
    }

    @Test
    void constructor_parentPathIsFile_throwsFileStorageException() throws IOException {
        Path parentFile = Files.createFile(tempDir.resolve("not-a-directory"));

        FileStorageException exception = assertThrows(
                FileStorageException.class, () -> new Storage(parentFile.resolve("tasks.txt")));

        assertEquals(
                "I can't initialize your task file. Check the data folder before bothering me again.",
                exception.getMessage());
    }

    @Test
    void saveAndLoad_allTaskTypes_preservesTaskData()
            throws FileStorageException, InvalidTaskNumberException, IOException {
        Path filePath = tempDir.resolve("tasks.txt");
        Storage storage = new Storage(filePath);
        TaskList originalTasks = new TaskList();
        originalTasks.addTask(new Todo("read | book", true));
        originalTasks.addTask(new Deadline(
                "submit | report", false, LocalDateTime.of(2026, 8, 30, 18, 0)));
        originalTasks.addTask(new Event(
                "project | meeting",
                true,
                LocalDateTime.of(2026, 8, 31, 9, 0),
                LocalDateTime.of(2026, 8, 31, 10, 30)));

        storage.save(originalTasks);
        TaskList loadedTasks = new TaskList();
        storage.load(loadedTasks);

        assertEquals(3, loadedTasks.getCount());
        assertInstanceOf(Todo.class, loadedTasks.getTask(1));
        assertInstanceOf(Deadline.class, loadedTasks.getTask(2));
        assertInstanceOf(Event.class, loadedTasks.getTask(3));
        for (int index = 1; index <= originalTasks.getCount(); index++) {
            assertEquals(
                    originalTasks.getTask(index).toStorageString(),
                    loadedTasks.getTask(index).toStorageString());
        }
        assertEquals(List.of(
                "T | X | read | book",
                "D |   | submit | report | Aug 30 2026, 06:00 PM",
                "E | X | project | meeting | Aug 31 2026, 09:00 AM | Aug 31 2026, 10:30 AM"),
                Files.readAllLines(filePath));
    }

    @Test
    void save_emptyTaskList_truncatesStorageFile()
            throws FileStorageException, InvalidTaskNumberException, IOException {
        Path filePath = tempDir.resolve("tasks.txt");
        Files.writeString(filePath, "old task data");
        Storage storage = new Storage(filePath);

        storage.save(new TaskList());

        assertTrue(Files.readString(filePath).isEmpty());
    }

    @Test
    void load_malformedLines_throwsFileStorageException() throws IOException, FileStorageException {
        Path filePath = tempDir.resolve("tasks.txt");
        Storage storage = new Storage(filePath);
        List<String> malformedLines = List.of(
                "T",
                "T | ? | task",
                "Z |   | task",
                "T |   | ",
                "D |   | task | invalid date",
                "E |   | event | Aug 31 2026, 10:30 AM | Aug 31 2026, 09:00 AM");

        for (String malformedLine : malformedLines) {
            Files.writeString(filePath, malformedLine);
            FileStorageException exception = assertThrows(
                    FileStorageException.class, () -> storage.load(new TaskList()));
            assertEquals(
                    "Why did you change the task file? I can't load your tasks now.",
                    exception.getMessage());
        }
    }

    @Test
    void load_missingFile_throwsFileStorageException() throws IOException, FileStorageException {
        Path filePath = tempDir.resolve("tasks.txt");
        Storage storage = new Storage(filePath);
        Files.delete(filePath);

        FileStorageException exception = assertThrows(
                FileStorageException.class, () -> storage.load(new TaskList()));

        assertEquals(
                "I can't read your task file. Did you move it while I wasn't looking?",
                exception.getMessage());
    }

    @Test
    void save_filePathBecomesDirectory_throwsFileStorageException()
            throws IOException, FileStorageException {
        Path filePath = tempDir.resolve("tasks.txt");
        Storage storage = new Storage(filePath);
        Files.delete(filePath);
        Files.createDirectory(filePath);

        FileStorageException exception = assertThrows(
                FileStorageException.class, () -> storage.save(new TaskList()));

        assertEquals(
                "I can't save your tasks. Check the task file before bothering me again.",
                exception.getMessage());
    }
}
