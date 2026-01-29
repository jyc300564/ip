package shallowseek;

/**
 * Represents an abstract command in the ShallowSeek application.
 *
 * A {@code Command} encapsulates a single user action that can be executed
 * within a given application context. Concrete subclasses define specific
 * behaviours such as adding tasks, deleting tasks, or exiting the program.
 *
 * This abstraction enables a command-based architecture, allowing user input
 * to be parsed into command objects and executed in a uniform way.
 */
public abstract class Command {

    /**
     * Executes this command using the provided application context.
     *
     * Subclasses should implement this method to define the command's behaviour,
     * potentially modifying application state and producing a {@link CommandResult}
     * that describes the outcome of execution.
     *
     * @param context the current application context containing shared state
     * @return the result of executing this command
     */
    public abstract CommandResult execute(Context context);
}
