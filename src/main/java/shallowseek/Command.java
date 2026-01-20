package shallowseek;

public abstract class Command {
    public abstract CommandResult execute(String input, Context context);
}
