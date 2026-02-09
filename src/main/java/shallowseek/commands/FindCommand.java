package shallowseek.commands;

import java.util.List;

import shallowseek.Command;
import shallowseek.CommandResult;
import shallowseek.Task;
import shallowseek.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Iterate throught the task list to find tasks with description containing provided keyword.
     * @param the task list
     * @return a CommandResult item containing string representation of matching tasks
     */
    @Override
    public CommandResult execute(TaskList context) {
        List<String> matches = context.getTaskList().stream()
            .filter(task -> task.getDescription().toLowerCase().contains(this.keyword))
            .map(Task::toString)
            .toList();

        if (matches.isEmpty()) {
            String message = "Performing shallow seek...\n"
            + "No matching tasks found.";
            return new CommandResult(message);
        }

        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (String taskStr : matches) {
            sb.append("  ")
                .append(count++)
                .append(". ")
                .append(taskStr)
                .append("\n");
        }

        String message = "Performing shallow seek...\n"
            + "Matching tasks found:\n"
            + sb.toString();
        return new CommandResult(message);
    }
}
