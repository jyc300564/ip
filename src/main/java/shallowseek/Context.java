package shallowseek;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the execution context of the ShallowSeek application.
 * This class manages the storage, retrieval, and state manipulation of tasks.
 */
public class Context {
    /** The list containing all tasks in the current context. */
    private List<Task> taskList;

    /**
     * Initializes a new Context with an empty task list.
     */
    public Context() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Retrieves the entire list of tasks.
     * @return A List of Task objects.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Set the list of tasks using the task list provided.
     * @param A List of Task objects.
     */
    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the total number of tasks currently in the list.
     * @return The size of the task list.
     */
    public int getTaskListSize() {
        return this.taskList.size();
    }

    /**
     * Retrieves a task at a specific position.
     * * @param index The zero-based index of the task to retrieve.
     * @return The Task object at the specified index.
     */
    public Task getTaskAt(int index) {
        return this.taskList.get(index);
    }

    /**
     * Adds a new task to the collection.
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes a task from the list at the specified index.
     * @param index The zero-based index of the task to be deleted.
     * @return The Task object that was removed.
     */
    public Task deleteTask(int index) {
        return this.taskList.remove(index);
    }

    /**
     * Marks the task at the specified index as completed.
     * @param index The zero-based index of the task.
     */
    public void markTaskAsDone(int index) {
        this.taskList.get(index).markAsDone();
    }

    /**
     * Unmarks the task at the specified index, setting its status to not done.
     * @param index The zero-based index of the task.
     */
    public void unmarkTaskAsDone(int index) {
        this.taskList.get(index).unmarkAsDone();
    }

    /**
     * Generates a formatted string representation of the task list.
     * If the list is empty, returns a specific message indicating no tasks were found.
     * @return A numbered string listing all tasks, or an empty-state message.
     */
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
