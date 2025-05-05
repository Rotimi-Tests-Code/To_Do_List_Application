package repository;

import model.Status;
import model.Task;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**Created a File Task  that reads into a given documentation*/

public class FileTaskRepository {
    private final String FILE_PATH = "tasks.txt";

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader (new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Format: id|description|status
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    int id  = Integer.parseInt(parts[0]);
                    String description = parts[1];
                    Status status = Status.valueOf(parts[2]);
                    Task task = new Task(id, description);
                    if (status == Status.DONE) task.markAsDone();
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return tasks;
    }

    public void saveTasks(List<Task> tasks) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task: tasks) {
                writer.write(task.getId() + "|" + task.getDescription() + "|" + task.getStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save tasks: " + e.getMessage());
        }
    }
}
