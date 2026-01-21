package shallowseek;
import java.util.ArrayList;
import java.util.List;

public class Context {
    private List<Task> taskList;

    public Context() {
        this.taskList = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public int getTaskListSize() {
        return this.taskList.size();
    }

    public Task getTaskAt(int index) {
        return this.taskList.get(index);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void markTaskAsDone(int index) {
        this.taskList.get(index).markAsDone();
    }

    public void unmarkTaskAsDone(int index) {
        this.taskList.get(index).unmarkAsDone();
    }

    public String taskListToString() {
        if (this.taskList.isEmpty()) {
            return "0 tasks to list. ShallowSeek found nothing on the surface.";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            sb.append(index).append(".")
                .append(this.taskList.get(i).toString()).append("\n");
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
