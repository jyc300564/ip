package shallowseek;

/**
 * Represents an abstract task within the ShallowSeek application.
 * This class serves as a base for specific task types, providing common
 * properties such as description and completion status.
 */
public abstract class Task {
    /** The description of the task. */
    private String description;
    /** The completion status of the task. */
    private boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially marked as not done.
     * @param description The textual description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the specified description.
     * @param description The textual description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the description of the task.
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task is completed.
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Resets the task status to not completed.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Converts the task into a specific string format suitable for file storage.
     * This format should allow the task to be reconstructed when the application restarts.
     * @return A formatted string representing the task's data for saving.
     */
    public abstract String toSaveString();

    /**
     * Returns a string representation of the task, including its completion status.
     * A completed task is prefixed with "[X]", while an incomplete one uses "[ ]".
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return this.isDone
            ? "[X] " + this.description
            : "[ ] " + this.description;
    }
}
