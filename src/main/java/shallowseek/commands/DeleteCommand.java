package shallowseek.commands;

import shallowseek.Command;
import shallowseek.CommandResult;
import shallowseek.TaskList;
import shallowseek.Task;

/**
 * A command that removes an existing task from the application context based on its index.
 */
public class DeleteCommand extends Command {
    /** The index of the task to be deleted. */
    int index;

    /**
     * Constructs a DeleteCommand for the task at the given index.
     * @param index The zero-based index of the task to remove.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the deletion logic. Includes validation to prevent index out of bounds errors.
     * @param context The application context containing the task list.
     * @return A CommandResult indicating either the deleted task details or an error message if the index is invalid.
     */
    @Override
    public CommandResult execute(TaskList context) {
        if (index > context.getTaskListSize() - 1) {
            String message = "Exception in thread \"main\" java.lang.IndexOutOfBoundsExcept...\n" +
            "Except I saw it coming!\n" +
            "You only have " + context.getTaskListSize() + " task(s) in the task list.";
            return new CommandResult(message);
        } else {
            Task deletedTask = context.deleteTask(this.index);
            return new CommandResult(
                "Deleted:\n  " +
                deletedTask.toString() +
                "\nYou now have " + context.getTaskListSize() + " task(s) in the list.");
        }
    }
}
