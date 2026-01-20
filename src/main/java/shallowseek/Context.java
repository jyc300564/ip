package shallowseek;
import java.util.ArrayList;
import java.util.List;

public class Context {
    private List<String> taskList;

    public Context() {
        this.taskList = new ArrayList<>();
    }

    public List<String> getTaskList() {
        return this.taskList;
    }

    public void addTask(String task) {
        this.taskList.add(task);
    }

    public String taskListToString() {
        if (this.taskList.isEmpty()) {
            return "0 tasks to list. ShallowSeek found nothing on the surface.";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            int serialNum = i + 1;
            sb.append(serialNum).append(". ")
                .append(this.taskList.get(i)).append("\n");
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
