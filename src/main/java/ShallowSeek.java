public class ShallowSeek {
    public static void main(String[] args) {
        Ui ui = new Ui();

        ui.showGreeting();

        CommandResult result = new ExitCommand().execute();
        ui.showExit(result);
    }
}
