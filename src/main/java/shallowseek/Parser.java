package shallowseek;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import shallowseek.commands.AddCommand;
import shallowseek.commands.DeleteCommand;
import shallowseek.commands.EmptyCommand;
import shallowseek.commands.ExitCommand;
import shallowseek.commands.ListCommand;
import shallowseek.commands.MarkCommand;
import shallowseek.commands.UnmarkCommand;
import shallowseek.exceptions.ShallowSeekException;
import shallowseek.tasks.Deadline;
import shallowseek.tasks.Event;
import shallowseek.tasks.ToDo;

/**
 * Handles the interpretation of user input strings into executable Command objects.
 * This class uses a factory pattern mapped to command keywords for modular parsing.
 */
public class Parser {
    /** A map associating command keywords with their respective functional factories. */
    private Map<String, CommandFactory> factories;

    /**
     * Initializes the parser and populates the command factory map with supported operations.
     */
    public Parser() {
        this.factories = new HashMap<>();

        factories.put("bye", args -> new ExitCommand());
        factories.put("list", args -> new ListCommand());
        factories.put("mark", args -> new MarkCommand(this.parseIndex(args)));
        factories.put("unmark", args -> new UnmarkCommand(this.parseIndex(args)));
        factories.put("delete", args -> new DeleteCommand(this.parseIndex(args)));
        factories.put("todo", args -> new AddCommand(this.parseTodo(args)));
        factories.put("deadline", args -> new AddCommand(this.parseDeadline(args)));
        factories.put("event", args -> new AddCommand(this.parseEvent(args)));
    }

    /**
     * Parses a string argument into a zero-based integer index.
     * @param args The string input representing a 1-based index.
     * @return The zero-based integer index.
     * @throws ShallowSeekException If the index is missing, not a number, or less than 1.
     */
    private int parseIndex(String args) throws ShallowSeekException {
        if (args.isEmpty()) {
            throw new ShallowSeekException("Index is missing.");
        }

        int index = 0;

        try {
            index = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new ShallowSeekException("Index must be a number.");
        }

        if (index <= 0) {
            throw new ShallowSeekException("Index must be greater than 0.");
        }

        return Integer.parseInt(args) - 1;
    }

    /**
     * Parses a date and time string into a LocalDateTime object using a specific pattern.
     * The expected format is "yyyy-MM-dd HH:mm".
     * @param dateTime The string containing the date and time information.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws ShallowSeekException If the input format is incorrect.
     */
    private LocalDateTime parseDate(String input) throws ShallowSeekException {
        try {
            if (input.matches("\\d{4}-\\d{2}-\\d{2}$")) {
                DateTimeFormatter fmt =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(input, fmt);
                return date.plusDays(1).atStartOfDay();
            } else {
                DateTimeFormatter fmt =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                return LocalDateTime.parse(input, fmt);
            }
        } catch (DateTimeParseException e) {
            throw new ShallowSeekException(
                "Invalid date format. Use yyyy-MM-dd or yyyy-MM-dd HH:mm.");
        }
    }

    /**
     * Parses the arguments for a ToDo task.
     * @param args The description of the task.
     * @return A new ToDo object.
     * @throws ShallowSeekException If the description is empty.
     */
    private ToDo parseTodo(String args) throws ShallowSeekException {
        String desc = args.trim();
        if (desc.isEmpty()) {
            throw new ShallowSeekException("Task description cannot be empty.");
        }

        return new ToDo(desc);
    }

    /**
     * Parses the arguments for a Deadline task.
     * @param args The input string containing description and "/by" delimiter.
     * @return A new Deadline object.
     * @throws ShallowSeekException If the format is incorrect or fields are empty.
     */
    private Deadline parseDeadline(String args) throws ShallowSeekException {
        String[] parts = args.split("\\s+/by\\s+", 2);
        if (parts.length < 2) {
            throw new ShallowSeekException(
                "Input format is incorrect.\n" +
                "Deadline Format: deadline <desc> /by <date/time>");
        }

        String desc = parts[0].trim();
        String deadline = parts[1].trim();
        if (desc.isEmpty()) {
            throw new ShallowSeekException("Task description cannot be empty.");
        }
        if (deadline.isEmpty()) {
            throw new ShallowSeekException(
                "This task needs a deadline.\n" +
                "Deadline Format: deadline <desc> /by <date/time>");
        }

        return new Deadline(desc, this.parseDate(deadline));
    }

    /**
     * Parses the arguments for an Event task.
     * @param args The input string containing description, "/from", and "/to" delimiters.
     * @return A new Event object.
     * @throws ShallowSeekException If the format is incorrect or time specifications are missing.
     */
    private Event parseEvent(String args) throws ShallowSeekException {
        String[] parts = args.split("\\s+/from\\s+", 2);
        if (parts.length < 2) {
            throw new ShallowSeekException(
                "Input format is incorrect.\n" +
                "Event format: event <desc> /from <start> /to <end>");
        }

        String desc = parts[0].trim();
        if (desc.isEmpty()) {
            throw new ShallowSeekException("Task description cannot be empty.");
        }

        String[] timeSpec = parts[1].trim().split("\\s+/to\\s+", 2);
        if (timeSpec.length < 2) {
            throw new ShallowSeekException(
                "This task needs a start time and a end time\n" +
                "Event format: event <desc> /from <start> /to <end>");
        }

        String start = timeSpec[0].trim();
        String end = timeSpec[1].trim();
        if (start.isEmpty() || end.isEmpty()) {
            throw new ShallowSeekException(
                "This task needs a start time and a end time\n" +
                "Event format: event <desc> /from <start> /to <end>");
        }

        return new Event(desc, this.parseDate(start), this.parseDate(end));
    }

    /**
     * Processes a full user input line and returns the corresponding Command.
     * @param input The raw input string from the user.
     * @return A Command object ready for execution.
     * @throws ShallowSeekException If the command is unknown or input is invalid.
     */
    public Command parse(String input) throws ShallowSeekException {
        String trimmed = input.trim();

        if (trimmed.isEmpty()) {
            return new EmptyCommand();
        }

        String[] parts = trimmed.split("\\s+", 2);
        String commandWord = parts[0];
        String args = parts.length < 2 ? "" : parts[1].trim();

        CommandFactory factory = factories.get(commandWord);
        if (factory == null) {
            String message = "Unknown command...\nToo deep for me to seek.";
            throw new ShallowSeekException(message);
        }

        return factory.create(args);
    }
}
