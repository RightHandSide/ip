package yuno.action;

import yuno.exception.YunoException;
import yuno.storage.Storage;
import yuno.task.TaskList;
import yuno.ui.Ui;

/**
 * Represents a user command and executes its associated behavior.
 */
public abstract class Action {
    /** Stores the task description or task index supplied with this action. */
    private final String taskDescription;

    /**
     * Creates an action with the specified command data.
     *
     * @param taskDescription Task description or task index used by the action.
     */
    public Action(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Executes this action using the specified task list, user interface, and storage.
     *
     * @param taskList Task list to read from or modify.
     * @param ui User interface used to display results.
     * @param storage Storage used to save task list changes.
     * @return False if this action ends the program; otherwise, true.
     * @throws YunoException If the command cannot be completed due to invalid input or task data.
     */
    public abstract boolean execute(TaskList taskList, Ui ui, Storage storage) throws YunoException;
}
