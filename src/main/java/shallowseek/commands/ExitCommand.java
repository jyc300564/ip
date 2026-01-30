package shallowseek.commands;
import shallowseek.Command;
import shallowseek.CommandResult;
import shallowseek.TaskList;

/**
 * Represents a command that signals the application to terminate.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit sequence and returns a result indicating the app should close.
     * @param context The application context (unused by this command).
     * @return A CommandResult with a farewell message and the exit flag set to true.
     */
    @Override
    public CommandResult execute(TaskList context) {
        return new CommandResult("Bye. No deep thinking was involved.", true);
    }
}
