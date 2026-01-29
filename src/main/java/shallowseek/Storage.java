package shallowseek;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import shallowseek.exceptions.ShallowSeekException;
import shallowseek.tasks.Deadline;
import shallowseek.tasks.Event;
import shallowseek.tasks.ToDo;

public class Storage {
    private final File file;

    public Storage() {
        this.file = new File("data/shallowseek.txt");
    }
    
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
            String deadline = parts[3];
            return new Deadline(desc, isDone, deadline);
        } else if (type.equals("E")) {
            if (parts.length < 5) {
                throw new ShallowSeekException("Corrupted save file line: " + line);
            }
            String startTime = parts[3];
            String endTime = parts[4];
            return new Event(desc, isDone, startTime, endTime);
        }

        throw new ShallowSeekException("Corrupted save file line: " + line);
    }

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
