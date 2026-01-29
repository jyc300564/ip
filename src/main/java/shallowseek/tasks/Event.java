package shallowseek.tasks;

import shallowseek.Task;

/**
 * Represents a task that occurs during a specific time period.
 * It stores a start time and an end time in addition to the task description.
 */
public class Event extends Task {
    /** The starting time or date of the event. */
    String startTime;
    /** The ending time or date of the event. */
    String endTime;

    /**
     * Constructs a new Event task with start and end time specifications.
     * @param description The textual description of the event.
     * @param startTime The beginning of the event.
     * @param endTime The conclusion of the event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the event, 
     * including the task type identifier [E] and the time range.
     * @return A formatted string representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
            String.format(" (from: %s to: %s)", this.startTime, this.endTime);
    }
}
