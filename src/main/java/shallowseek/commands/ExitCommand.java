package shallowseek.commands;
import shallowseek.Command;
import shallowseek.CommandResult;
import shallowseek.Context;

public class ExitCommand extends Command {
    @Override
    public CommandResult execute(String input, Context context) {
        return new CommandResult("Bye. No deep thinking was involved.", true);
    }
}
