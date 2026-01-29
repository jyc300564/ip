package shallowseek.tasks;

import shallowseek.Task;

/**
 * Represents a task that has a specific deadline.
 * It extends the base Task class by adding a deadline time string.
 */
public class Deadline extends Task {
    /** The date or time by which the task must be completed. */
    private String deadline;

    /**
     * Constructs a new Deadline task with a description and a due date.
     * @param description The textual description of the deadline task.
     * @param deadline The time or date constraint for the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructs a new Deadline task with a description and a due date.
     * @param description The textual description of the deadline task.
     * @param isDone The completion status of the deadline task.
     * @param deadline The time or date constraint for the task.
     */
    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Converts the task into a specific string format suitable for file storage.
     * This format should allow the task to be reconstructed when the application restarts.
     * @return A formatted string representing the task's data for saving.
     */
    @Override
    public String toSaveString() {
        return "D|" +
            (this.isDone() ? "1" : "0") + "|" +
            this.getDescription() + "|" +
            this.deadline;
    }

    /**
     * Returns a string representation of the deadline task, 
     * including the task type identifier [D] and the deadline info.
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
