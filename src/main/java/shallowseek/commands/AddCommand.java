package shallowseek.commands;
import shallowseek.Command;
import shallowseek.CommandResult;
import shallowseek.Context;
import shallowseek.Task;

public class AddCommand extends Command {
    private String description;

    public AddCommand(String description) {
        this.description = description;
    }

    @Override
    public CommandResult execute(Context context) {
        Task newTask = new Task(this.description);
        context.addTask(newTask);
        return new CommandResult("added: " + newTask.toString());
    }
}
