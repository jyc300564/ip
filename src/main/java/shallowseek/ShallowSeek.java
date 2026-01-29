package shallowseek;

import shallowseek.commands.ErrorCommand;
import shallowseek.exceptions.ShallowSeekException;

/**
 * The main entry point for the ShallowSeek application.
 * This class coordinates the user interface, command parsing, and execution context.
 */
public class ShallowSeek {
    /** The storage and state manager for tasks. */
    private Context context;
    /** The user interface handler for input and output. */
    private Ui ui;
    /** The parser used to translate user input into executable commands. */
    private Parser parser;

    /**
     * Initializes a new instance of ShallowSeek with its required components.
     */
    public ShallowSeek() {
        this.context = new Context();
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Starts the main program loop, handling the input-parse-execute cycle.
     * The loop continues until an exit command is issued by the user.
     */
    private void run() {
        ui.showGreeting();

        while (true) {
            String input = ui.readInput();
            Command command;
            try {
                command = parser.parse(input);
            } catch (ShallowSeekException e) {
                command = new ErrorCommand(e.getMessage());
            } 

            CommandResult result = command.execute(context);
            ui.showResult(result);
            if (result.shouldExit()) {
                break;
            }
        }
    }

    /**
     * The main method that launches the ShallowSeek application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new ShallowSeek().run();
    }
}
