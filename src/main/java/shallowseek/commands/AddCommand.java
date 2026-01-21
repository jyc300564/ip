package shallowseek.commands;
import shallowseek.Command;
import shallowseek.CommandResult;
import shallowseek.Context;
import shallowseek.Task;

public class AddCommand extends Command {
    private Task newTask;

    public AddCommand(Task taskToAdd) {
        this.newTask = taskToAdd;
    }

    @Override
    public CommandResult execute(Context context) {
        context.addTask(newTask);
        return new CommandResult("added: " + newTask.toString());
    }
}
