package shallowseek.commands;

import shallowseek.Command;
import shallowseek.CommandResult;
import shallowseek.Context;

/**
 * A command that reverts a specific task's status to not completed.
 * It identifies the task using a zero-based index.
 */
public class UnmarkCommand extends Command {
    /** The index of the task to be unmarked. */
    int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     * @param index The zero-based index of the task in the task list.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmarking logic. Checks if the index is within bounds before
     * updating the task status in the context.
     * @param context The application context containing the task list.
     * @return A CommandResult containing a confirmation message or an error message.
     */
    public CommandResult execute(Context context) {
        if (index > context.getTaskListSize() - 1) {
            String message = "Exception in thread \"main\" java.lang.IndexOutOfBoundsExcept...\n" +
            "Except I saw it coming!\n" +
            "You only have " + context.getTaskListSize() + " task(s) in the task list.";
            return new CommandResult(message);
        } else {
            context.unmarkTaskAsDone(index);
            String message = "Performing shallow seek...\n" +
                "Task marked as not done:\n  " +
                context.getTaskAt(this.index).toString();
            return new CommandResult(message);
        }
    }
}
