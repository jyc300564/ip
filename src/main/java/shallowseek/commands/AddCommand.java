package shallowseek.commands;
import shallowseek.Command;
import shallowseek.CommandResult;
import shallowseek.TaskList;
import shallowseek.Task;

/**
 * A command that adds a new task to the application context.
 */
public class AddCommand extends Command {
    /** The task to be added to the context. */
    private Task newTask;

    /**
     * Constructs an AddCommand with the specified task.
     * @param taskToAdd The task object to be included in the list.
     */
    public AddCommand(Task taskToAdd) {
        this.newTask = taskToAdd;
    }

    /**
     * Executes the addition of the task and returns a confirmation result.
     * @param context The application context where the task will be saved.
     * @return A CommandResult containing the success message and updated list size.
     */
    @Override
    public CommandResult execute(TaskList context) {
        context.addTask(newTask);
        return new CommandResult(
            "Added:\n  " +
            newTask.toString() +
            "\nYou now have " + context.getTaskListSize() + " task(s) in the list.");
    }
}
