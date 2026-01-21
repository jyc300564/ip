package shallowseek.commands;

import shallowseek.Command;
import shallowseek.CommandResult;
import shallowseek.Context;
import shallowseek.Task;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Context context) {
        if (index > context.getTaskListSize() - 1) {
            String message = "Exception in thread \"main\" java.lang.IndexOutOfBoundsExcept...\n" +
            "Except I saw it coming!\n" +
            "You only have " + context.getTaskListSize() + " task(s) in the task list.";
            return new CommandResult(message);
        } else {
            Task deletedTask = context.deleteTask(this.index);
            return new CommandResult(
                "Deleted:\n  " +
                deletedTask.toString() +
                "\nYou now have " + context.getTaskListSize() + " task(s) in the list.");
        }
    }
}
