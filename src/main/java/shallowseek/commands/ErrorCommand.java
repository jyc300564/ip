package shallowseek.commands;

import shallowseek.Command;
import shallowseek.CommandResult;
import shallowseek.Context;

/**
 * Represents a command that is created when an error occurs during parsing or execution.
 */
public class ErrorCommand extends Command {
    /** The error message to be displayed to the user. */
    private String message;

    /**
     * Constructs an ErrorCommand with a specific error message.
     * @param message The details of the error.
     */
    public ErrorCommand(String message) {
        this.message = message;
    }

    /**
     * Returns a result containing the error message.
     * @param context The application context (unused by this command).
     * @return A CommandResult containing the formatted error message.
     */
    @Override
    public CommandResult execute(Context context) {
        return new CommandResult("Shallow mistake detected: " +
            this.message);
    }
}
