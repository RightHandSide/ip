package yuno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import yuno.exception.FileStorageException;
import yuno.exception.InvalidTaskNumberException;
import yuno.parser.Parser;
import yuno.storage.Storage;
import yuno.task.TaskList;
import yuno.ui.Ui;

class YunoTest {
    @TempDir
    private Path tempDir;
    private final InputStream originalInput = System.in;
    private final PrintStream originalOutput = System.out;

    @AfterEach
    void restoreSystemStreams() {
        System.setIn(originalInput);
        System.setOut(originalOutput);
    }

    @Test
    void run_invalidThenValidCommands_reportsErrorAndContinuesUntilBye()
            throws FileStorageException, InvalidTaskNumberException, IOException {
        System.setIn(new ByteArrayInputStream(
                "nonsense\ntodo read book\nbye\n".getBytes(StandardCharsets.UTF_8)));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Path filePath = tempDir.resolve("tasks.txt");
        Storage storage = new Storage(filePath);
        Yuno yuno = new Yuno(ui, new Parser(), storage, taskList);

        yuno.run();

        String consoleOutput = output.toString(StandardCharsets.UTF_8);
        assertEquals(1, taskList.getCount());
        assertEquals("read book", taskList.getTask(1).getDescription());
        assertEquals("T |   | read book", Files.readString(filePath).stripTrailing());
        assertTrue(consoleOutput.contains(
                "Did you look at what you typed? That's just a random command."));
        assertTrue(consoleOutput.contains("Added:"));
        assertTrue(consoleOutput.contains("Finally! Bye. I'm leaving!"));
    }
}
