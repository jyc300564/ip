package shallowseek.commands;

import shallowseek.Command;
import shallowseek.CommandResult;
import shallowseek.Task;
import shallowseek.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(TaskList context) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Task task : context.getTaskList()) {
            if (task.getDescription().toLowerCase().contains(this.keyword)) {
                sb.append("  ");
                sb.append(count + ". ");
                sb.append(task.toString());
                sb.append("\n");
                count++;
            }
        }

        if (sb.isEmpty()) {
            String message = "Performing shallow seek...\n"
                + "No matching tasks found.";
            return new CommandResult(message);
        } else {
            String message = "Performing shallow seek...\n"
                + "Matching tasks found:\n"
                + sb.toString();
            return new CommandResult(message);
        }
    }
}
