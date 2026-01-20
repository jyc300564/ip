package shallowseek;

public class CommandResult {
    private String message;
    private boolean shouldExit;

    public CommandResult(String message) {
        this.message = message;
        this.shouldExit = false;
    }

    public CommandResult(String message, boolean shouldExit) {
        this.message = message;
        this.shouldExit = shouldExit;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean shouldExit() {
        return this.shouldExit;
    }
}
