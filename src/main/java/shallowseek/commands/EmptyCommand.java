package shallowseek.commands;

import shallowseek.Command;
import shallowseek.CommandResult;
import shallowseek.TaskList;

/**
 * Represents a command executed when the user provides empty or whitespace-only input.
 */
public class EmptyCommand extends Command {
    /**
     * Returns a minimal response indicating that no action was taken.
     * @param context The application context (unused by this command).
     * @return A CommandResult containing a simple ellipsis.
     */
    @Override
    public CommandResult execute(TaskList context) {
        return new CommandResult("...");
    }
}
