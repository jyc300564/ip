package shallowseek.commands;
import shallowseek.Command;
import shallowseek.CommandResult;
import shallowseek.Context;

public class ListCommand extends Command {
    @Override
    public CommandResult execute(String input, Context context) {
        return new CommandResult(context.taskListToString());
    }
}
