package shallowseek.commands;

import shallowseek.Command;
import shallowseek.CommandResult;
import shallowseek.Context;

public class ErrorCommand extends Command {
    private String message;

    public ErrorCommand(String message) {
        this.message = message;
    }

    @Override
    public CommandResult execute(Context context) {
        return new CommandResult("Shallow mistake detected: " +
            this.message);
    }
}
