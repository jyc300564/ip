package shallowseek;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import shallowseek.exceptions.ShallowSeekException;
import shallowseek.tasks.Deadline;
import shallowseek.tasks.Event;
import shallowseek.tasks.ToDo;

/**
 * Handles the loading and saving of task data to a local file.
 * This class ensures task persistence across different application sessions.
 */
public class Storage {
    /** The file object representing the local storage file. */
    private final File file;

    /**
     * Initializes a Storage object with a default file path at "data/shallowseek.txt".
     */
    public Storage() {
        this.file = new File("data/shallowseek.txt");
    }
    
    /**
     * Parses a single line from the save file into a Task object.
     * @param line A vertical-bar-separated string representing a task.
     * @return The reconstructed Task object (ToDo, Deadline, or Event).
     * @throws ShallowSeekException If the line format is corrupted or the task type is unknown.
     */
    private Task parseTask(String line) throws ShallowSeekException {
        String[] parts = line.split("\\|");
        if (parts.length < 3) {
            throw new ShallowSeekException("Corrupted save file line: " + line);
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1") ? true : false;
        String desc = parts[2];

        if (type.equals("T")) {
            return new ToDo(desc, isDone);
        } else if (type.equals("D")) {
            if (parts.length < 4) {
                throw new ShallowSeekException("Corrupted save file line: " + line);
            }
            DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime deadline = LocalDateTime.parse(parts[3], fmt);
            return new Deadline(desc, isDone, deadline);
        } else if (type.equals("E")) {
            if (parts.length < 5) {
                throw new ShallowSeekException("Corrupted save file line: " + line);
            }
            DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime startTime = LocalDateTime.parse(parts[3], fmt);
            LocalDateTime endTime = LocalDateTime.parse(parts[4], fmt);
            return new Event(desc, isDone, startTime, endTime);
        }

        throw new ShallowSeekException("Corrupted save file line: " + line);
    }

    /**
     * Loads the task list from the local storage file.
     * If the file does not exist, an empty list is returned.
     * @return A list of tasks retrieved from the file.
     * @throws FileNotFoundException If the file cannot be accessed.
     * @throws ShallowSeekException If the data within the file is corrupted.
     */
    public List<Task> load() throws FileNotFoundException, ShallowSeekException {
        if (!file.exists()) {
            return new ArrayList<>();
        }

        Scanner sc = new Scanner(file);
        List<Task> taskList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            try {
                taskList.add(this.parseTask(line));
            } catch (ShallowSeekException e) {
                sc.close();
                throw e;
            }
        }

        sc.close();
        return taskList;
    }

    /**
     * Saves the current list of tasks to the local storage file.
     * Creates the necessary directories if they do not exist.
     * @param taskList The list of Task objects to be persisted.
     * @throws IOException If an error occurs during the file writing process.
     */
    public void save(List<Task> taskList) throws IOException {
        File dir = this.file.getParentFile();
        if (dir != null && !dir.exists()) {
            dir.mkdirs();
        }

        FileWriter writer = new FileWriter(file);
        for (Task task : taskList) {
            writer.write(task.toSaveString());
            writer.write(System.lineSeparator());
        }
        writer.close();
    }
}
