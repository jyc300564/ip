package shallowseek.tasks;

import shallowseek.Task;

/**
 * Represents a basic task without any specific time constraints.
 * It is identified by the [T] prefix in its string representation.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the specified description.
     * @param description The textual description of the task.
     */
    public ToDo(String description) {
        super(description);
    }


    /**
     * Constructs a new ToDo task with the specified description.
     * @param description The textual description of the task.
     * @param isDone The completion status of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Converts the task into a specific string format suitable for file storage.
     * This format should allow the task to be reconstructed when the application restarts.
     * @return A formatted string representing the task's data for saving.
     */
    @Override
    public String toSaveString() {
        return "T|" +
            (this.isDone() ? "1" : "0") + "|" +
            this.getDescription();
    }

    /**
     * Returns a string representation of the ToDo task, 
     * prefixed with the type identifier [T].
     * @return A formatted string representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
