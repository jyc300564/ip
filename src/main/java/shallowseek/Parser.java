package shallowseek;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import shallowseek.commands.AddCommand;
import shallowseek.commands.ErrorCommand;
import shallowseek.commands.ExitCommand;
import shallowseek.commands.ListCommand;
import shallowseek.commands.MarkCommand;
import shallowseek.commands.UnmarkCommand;

public class Parser {
    private Map<String, Function<String, Command>> factories;

    public Parser() {
        this.factories = new HashMap<>();

        factories.put("bye", args -> new ExitCommand());
        factories.put("list", args -> new ListCommand());
        factories.put("mark", args -> new MarkCommand(this.parseIndex(args)));
        factories.put("unmark", args -> new UnmarkCommand(this.parseIndex(args)));
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

    public Command parse(String input) {
        String trimmed = input.trim();

        if (trimmed.isEmpty()) {
            return new AddCommand("");
        }

        String[] parts = trimmed.split("\\s+", 2);
        String commandWord = parts[0];
        String args = parts.length < 2 ? "" : parts[1].trim();

        Function<String, Command> factory = factories.get(commandWord);
        if (factory == null) {
            return new AddCommand(trimmed);
        }

        try {
            return factory.apply(args);
        } catch (IllegalArgumentException e) {
            return new ErrorCommand(e.getMessage());
        }
    }
}
