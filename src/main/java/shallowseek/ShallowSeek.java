package shallowseek;

import java.io.IOException;

import shallowseek.commands.ErrorCommand;
import shallowseek.exceptions.ShallowSeekException;

/**
 * The main entry point for the ShallowSeek application.
 * This class coordinates the user interface, command parsing, and execution context.
 */
public class ShallowSeek {
    /** The storage and state manager for tasks. */
    private TaskList context;
    /** The user interface handler for input and output. */
    private Ui ui;
    /** The parser used to translate user input into executable commands. */
    private Parser parser;
    /** The persisten storage for the task list. */
    private Storage storage;

    /**
     * Initializes a new instance of ShallowSeek with its required components.
     */
    public ShallowSeek() {
        this.context = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage();

        this.loadTaskList();
    }

    /**
     * Loads the task list from persistent memory.
     */
    public void loadTaskList() {
        try {
            this.context.setTaskList(this.storage.load());
        } catch (ShallowSeekException e) {
            this.ui.showLoadError(e);
            return;
        } catch (IOException e) {
            this.ui.showLoadError(e);
            return;
        }
    }

    /**
     * Stores the task list into persistent memory.
     */
    public void storeTaskList() {
        try {
            this.storage.save(this.context.getTaskList());
        } catch (IOException e) {
            this.ui.showSaveError(e);
        }
    }

    /**
     * Gets the result of a user command.
     * @param input from user
     * @return the result of corresponding command, a ErrorCommand in case of exceptions
     */
    public CommandResult getResponse(String input) {
        Command command;
        try {
            command = this.parser.parseInput(input);
        } catch (ShallowSeekException e) {
            command = new ErrorCommand(e.getMessage());
        } 

        return command.execute(context);
    }
}
