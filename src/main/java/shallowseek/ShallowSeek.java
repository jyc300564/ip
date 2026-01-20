package shallowseek;
import java.util.HashMap;
import java.util.Map;

import shallowseek.commands.AddCommand;
import shallowseek.commands.ExitCommand;
import shallowseek.commands.ListCommand;

public class ShallowSeek {
    private Map<String, Command> commands;
    private Context context;
    private Ui ui;

    public ShallowSeek() {
        this.commands = new HashMap<>();
        this.context = new Context();
        this.ui = new Ui();

        this.commands.put("list", new ListCommand());
        this.commands.put("bye", new ExitCommand());
    }

    private void run() {
        ui.showGreeting();

        while (true) {
            String commandWord = ui.readInput().split(" ")[0];
            Command command = commands.getOrDefault(
                commandWord, new AddCommand());

            CommandResult result = command.execute(
                commandWord, this.context);
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
