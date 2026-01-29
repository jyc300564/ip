package shallowseek.commands;
import shallowseek.Command;
import shallowseek.CommandResult;
import shallowseek.Context;

/**
 * Represents a command that displays all current tasks in the list.
 */
public class ListCommand extends Command {
    /**
     * Retrieves the formatted task list from the context and returns it as a result.
     * @param context The application context containing the task list.
     * @return A CommandResult containing the string representation of all tasks.
     */
    @Override
    public CommandResult execute(Context context) {
        return new CommandResult(context.taskListToString());
    }
}
