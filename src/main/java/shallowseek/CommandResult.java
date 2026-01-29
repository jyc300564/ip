package shallowseek;

/**
 * Represents the result of executing a {@link Command}.
 *
 * A {@code CommandResult} encapsulates the outcome of a command execution,
 * including feedback to be shown to the user and whether the application
 * should terminate after the command completes.
 *
 * This class acts as a simple data carrier between the command execution
 * layer and the user interface.
 */
public class CommandResult {

    /**
     * The feedback message to be shown to the user after command execution.
     */
    private final String message;

    /**
     * Indicates whether the application should exit after this command.
     */
    private final boolean shouldExit;

    /**
     * Constructs a {@code CommandResult} with the specified feedback message.
     *
     * @param message the feedback message to display to the user
     */
    public CommandResult(String message) {
        this.message = message;
        this.shouldExit = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified feedback message
     * and exit flag.
     *
     * @param message the feedback message to display to the user
     * @param shouldExit {@code true} if the application should terminate;
     *                   {@code false} otherwise
     */
    public CommandResult(String message, boolean shouldExit) {
        this.message = message;
        this.shouldExit = shouldExit;
    }

    /**
     * Returns the feedback message associated with this command result.
     *
     * @return the feedback message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Returns whether the application should exit after this command.
     *
     * @return {@code true} if the application should terminate;
     *         {@code false} otherwise
     */
    public boolean shouldExit() {
        return this.shouldExit;
    }
}
