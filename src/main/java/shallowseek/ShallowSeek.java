package shallowseek;

import shallowseek.commands.ErrorCommand;
import shallowseek.exceptions.ShallowSeekException;

public class ShallowSeek {
    private Context context;
    private Ui ui;
    private Parser parser;

    public ShallowSeek() {
        this.context = new Context();
        this.ui = new Ui();
        this.parser = new Parser();
    }

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

    public static void main(String[] args) {
        new ShallowSeek().run();
    }
}
