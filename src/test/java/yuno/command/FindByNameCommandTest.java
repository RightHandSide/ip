package yuno.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import yuno.exception.YunoException;
import yuno.task.Task;

class FindByNameCommandTest extends CommandTestSupport {
    @Test
    void execute_matchingDescription_displaysMatchingTasks() throws YunoException {
        Task firstMatch = taskList.addTask("read project requirements");
        taskList.addTask("buy groceries");
        Task secondMatch = taskList.addTask("submit project report");

        boolean shouldContinue = new FindByNameCommand("project")
                .execute(taskList, ui, storage);

        assertTrue(shouldContinue);
        assertEquals(List.of(firstMatch, secondMatch), ui.displayedNameTasks);
    }

    @Test
    void execute_noMatchingDescription_displaysEmptyList() throws YunoException {
        taskList.addTask("read book");

        boolean shouldContinue = new FindByNameCommand("project")
                .execute(taskList, ui, storage);

        assertTrue(shouldContinue);
        assertTrue(ui.displayedNameTasks.isEmpty());
    }
}
