package shallowseek;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import shallowseek.commands.AddCommand;
import shallowseek.commands.EmptyCommand;
import shallowseek.commands.ErrorCommand;
import shallowseek.commands.ExitCommand;
import shallowseek.commands.ListCommand;
import shallowseek.commands.MarkCommand;
import shallowseek.commands.UnmarkCommand;
import shallowseek.tasks.Deadline;
import shallowseek.tasks.Event;
import shallowseek.tasks.ToDo;

public class Parser {
    private Map<String, Function<String, Command>> factories;

    public Parser() {
        this.factories = new HashMap<>();

        factories.put("bye", args -> new ExitCommand());
        factories.put("list", args -> new ListCommand());
        factories.put("mark", args -> new MarkCommand(this.parseIndex(args)));
        factories.put("unmark", args -> new UnmarkCommand(this.parseIndex(args)));
        factories.put("todo", args -> new AddCommand(this.parseTodo(args)));
        factories.put("deadline", args -> new AddCommand(this.parseDeadline(args)));
        factories.put("event", args -> new AddCommand(this.parseEvent(args)));
    }

    private int parseIndex(String args) {
        if (args.isEmpty()) {
            throw new IllegalArgumentException("Index is missing.");
        }

        int index = 0;

        try {
            index = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Index must be a number");
        }

        if (index <= 0) {
            throw new IllegalArgumentException("Index must be greater than 0.");
        }

        return Integer.parseInt(args) - 1;
    }

    private ToDo parseTodo(String args) {
        String desc = args.trim();
        if (desc.isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty.");
        }

        return new ToDo(desc);
    }

    private Deadline parseDeadline(String args) {
        String[] parts = args.split("\\s+/by\\s+", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException(
                "Input format is incorrect.\n" +
                "Deadline Format: deadline <desc> /by <date/time>");
        }

        String desc = parts[0].trim();
        String deadline = parts[1].trim();
        if (desc.isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty.");
        }
        if (deadline.isEmpty()) {
            throw new IllegalArgumentException(
                "This task needs a deadline.\n" +
                "Deadline Format: deadline <desc> /by <date/time>");
        }

        return new Deadline(desc, deadline);
    }

    private Event parseEvent(String args) {
        String[] parts = args.split("\\s+/from\\s+", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException(
                "Input format is incorrect.\n" +
                "Event format: event <desc> /from <start> /to <end>");
        }

        String desc = parts[0].trim();
        if (desc.isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty.");
        }

        String[] timeSpec = parts[1].trim().split("\\s+/to\\s+", 2);
        if (timeSpec.length < 2) {
            throw new IllegalArgumentException(
                "This task needs a start time and a end time\n" +
                "Event format: event <desc> /from <start> /to <end>");
        }

        String start = timeSpec[0].trim();
        String end = timeSpec[1].trim();
        if (start.isEmpty() || end.isEmpty()) {
            throw new IllegalArgumentException(
                "This task needs a start time and a end time\n" +
                "Event format: event <desc> /from <start> /to <end>");
        }

        return new Event(desc, start, end);
    }

    public Command parse(String input) {
        String trimmed = input.trim();

        if (trimmed.isEmpty()) {
            return new EmptyCommand();
        }

        String[] parts = trimmed.split("\\s+", 2);
        String commandWord = parts[0];
        String args = parts.length < 2 ? "" : parts[1].trim();

        Function<String, Command> factory = factories.get(commandWord);
        if (factory == null) {
            String message = "Unknown Command...\nToo deep for me to seek.";
            return new ErrorCommand(message);
        }

        try {
            return factory.apply(args);
        } catch (IllegalArgumentException e) {
            return new ErrorCommand(e.getMessage());
        }
    }
}
