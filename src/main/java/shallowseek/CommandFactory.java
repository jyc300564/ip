package shallowseek;

import shallowseek.exceptions.ShallowSeekException;

/**
 * Represents a factory for creating {@link Command} objects.
 *
 * A {@code CommandFactory} encapsulates the logic for constructing a specific
 * type of command from user-provided arguments. This allows command creation
 * logic to be decoupled from parsing and execution, supporting a flexible and
 * extensible command architecture.
 *
 * Implementations of this interface typically correspond to individual command
 * types and are used by the parser to generate executable commands.
 */
@FunctionalInterface
public interface CommandFactory {

    /**
     * Creates a {@link Command} instance using the provided argument string.
     *
     * Implementations should parse and validate the arguments, and construct
     * an appropriate command object. If the arguments are invalid or incomplete,
     * a {@link ShallowSeekException} should be thrown to signal a parsing error.
     *
     * @param args the argument string associated with the command
     * @return a newly created {@link Command} instance
     * @throws ShallowSeekException if the arguments are invalid or cannot be parsed
     */
    Command create(String args) throws ShallowSeekException;
}
