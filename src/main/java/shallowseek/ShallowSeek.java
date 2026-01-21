package shallowseek;

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
            Command command = parser.parse(input);
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
