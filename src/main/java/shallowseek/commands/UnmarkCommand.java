package shallowseek.commands;

import shallowseek.Command;
import shallowseek.CommandResult;
import shallowseek.Context;

public class UnmarkCommand extends Command {
    int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public CommandResult execute(Context context) {
        if (index > context.getTaskListSize() - 1) {
            String message = "Exception in thread \"main\" java.lang.IndexOutOfBoundsExcept...\n" +
            "Except I saw it coming!\n" +
            "You only have " + context.getTaskListSize() + " task(s) in the task list.";
            return new CommandResult(message);
        } else {
            context.unmarkTaskAsDone(index);
            String message = "Performing shallow seek...\n" +
                "Task marked as not done:\n  " +
                context.getTaskAt(this.index).toString();
            return new CommandResult(message);
        }
    }
}
