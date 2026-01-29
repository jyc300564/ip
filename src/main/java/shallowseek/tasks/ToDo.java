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
     * Returns a string representation of the ToDo task, 
     * prefixed with the type identifier [T].
     * @return A formatted string representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
