package shallowseek.exceptions;

/**
 * Represents a custom exception specific to the ShallowSeek application.
 * This exception is thrown when the application encounters parsing errors 
 * or invalid user commands.
 */
public class ShallowSeekException extends Exception {
    /**
     * Constructs a new ShallowSeekException with the specified detail message.
     * @param message The detail message explaining the cause of the exception.
     */
    public ShallowSeekException(String message) {
        super(message);
    }
}
