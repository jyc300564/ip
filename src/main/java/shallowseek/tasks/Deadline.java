package shallowseek.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import shallowseek.Task;

/**
 * Represents a task that has a specific deadline.
 * It extends the base Task class by adding a deadline time string.
 */
public class Deadline extends Task {

    /** The display format of deadline time */
    private static final DateTimeFormatter DISPLAY_FMT =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    /** The date or time by which the task must be completed. */
    private LocalDateTime deadline;

    /**
     * Constructs a new Deadline task with a description and a due date.
     * @param description The textual description of the deadline task.
     * @param deadline The time or date constraint for the task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructs a new Deadline task with a description and a due date.
     * @param description The textual description of the deadline task.
     * @param isDone The completion status of the deadline task.
     * @param deadline The time or date constraint for the task.
     */
    public Deadline(String description, boolean isDone, LocalDateTime deadline) {
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
            this.deadline.format(DISPLAY_FMT);
    }

    /**
     * Compares this task with another object for equality.
     * Two tasks are considered equal if they represent the same task content,
     * regardless of their completion status.
     *
     * @param obj the object to compare with
     * @return true if the given object represents the same task
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Deadline)) {
            return false;
        }

        Deadline other = (Deadline) obj;
        return this.getDescription().equals(other.getDescription())
            && this.deadline.equals(other.deadline);
    }

    /**
     * Returns a string representation of the deadline task, 
     * including the task type identifier [D] and the deadline info.
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
            + " (by: " + this.deadline.format(DISPLAY_FMT) + ")";
    }

}
