package shallowseek.commands;

import shallowseek.Command;
import shallowseek.CommandResult;
import shallowseek.TaskList;

/**
 * A command that marks a specific task in the list as completed.
 * It identifies the task using a zero-based index.
 */
public class MarkCommand extends Command {
    /** The index of the task to be marked as done. */
    private int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     * @param index The zero-based index of the task in the task list.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the marking logic. Validates the index against the current list size
     * and updates the task status if the index is valid.
     * @param context The application context containing the task list.
     * @return A CommandResult containing a success message or an error if the index is out of bounds.
     */
    public CommandResult execute(TaskList context) {
        if (index > context.getTaskListSize() - 1) {
            String message = "Exception in thread \"main\" java.lang.IndexOutOfBoundsExcept...\n"
            + "Except I saw it coming!\n"
            + "You only have "
            + context.getTaskListSize()
            + " task(s) in the task list.";
            return new CommandResult(message);
        } else {
            context.markTaskAsDone(this.index);
            String message = "Performing shallow seek...\n"
                + "Task marked as done:\n  "
                + context.getTaskAt(this.index).toString();
            return new CommandResult(message);
        }
    }
}
