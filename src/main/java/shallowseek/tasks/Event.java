package shallowseek.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import shallowseek.Task;

/**
 * Represents a task that occurs during a specific time period.
 * It stores a start time and an end time in addition to the task description.
 */
public class Event extends Task {
    /** The display format of deadline time */
    private static final DateTimeFormatter DISPLAY_FMT =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    /** The starting time or date of the event. */
    LocalDateTime startTime;
    /** The ending time or date of the event. */
    LocalDateTime endTime;

    /**
     * Constructs a new Event task with start and end time specifications.
     * @param description The textual description of the event.
     * @param startTime The beginning of the event.
     * @param endTime The conclusion of the event.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructs a new Event task with start and end time specifications.
     * @param description The textual description of the event.
     * @param isDone The completion status of the event.
     * @param startTime The beginning of the event.
     * @param endTime The conclusion of the event.
     */
    public Event(String description, boolean isDone, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts the task into a specific string format suitable for file storage.
     * This format should allow the task to be reconstructed when the application restarts.
     * @return A formatted string representing the task's data for saving.
     */
    @Override
    public String toSaveString() {
        return "E|" +
            (this.isDone() ? "1" : "0") + "|" +
            this.getDescription() + "|" +
            this.startTime.format(DISPLAY_FMT) + "|" +
            this.endTime.format(DISPLAY_FMT);
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

        if (!(obj instanceof Event)) {
            return false;
        }

        Event other = (Event) obj;
        return this.getDescription().equals(other.getDescription())
            && this.startTime.equals(other.startTime)
            && this.endTime.equals(other.endTime);
    }

    /**
     * Returns a string representation of the event, 
     * including the task type identifier [E] and the time range.
     * @return A formatted string representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
            String.format(" (from: %s to: %s)",
                this.startTime.format(DISPLAY_FMT),
                this.endTime.format(DISPLAY_FMT)
            );
    }
}
