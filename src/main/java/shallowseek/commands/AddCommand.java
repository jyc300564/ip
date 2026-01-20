package shallowseek.commands;
import shallowseek.Command;
import shallowseek.CommandResult;
import shallowseek.Context;

public class AddCommand extends Command {
    @Override
    public CommandResult execute(String input, Context context) {
        context.addTask(input);
        return new CommandResult("added: " + input);
    }
}
