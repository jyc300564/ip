public class ShallowSeek {
    public static void main(String[] args) {
        Ui ui = new Ui();

        ui.showGreeting();

        while (true) {
            String input = ui.readInput();
            if (input.equals("bye")) {
                break;
            }
            ui.echo(input);
        }

        CommandResult result = new ExitCommand().execute();
        ui.showExit(result);
    }
}
