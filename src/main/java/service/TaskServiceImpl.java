package service;

import model.Status;
import model.Task;
import repository.FileTaskRepository;

import java.util.ArrayList;
import java.util.List;
/** The actual implementation of all items in the task service */
public class TaskServiceImpl implements TaskService {
   // private final List<Task> tasks = new ArrayList<>();
    private final List<Task> tasks;
    private final FileTaskRepository repository;
    private int nextId;

    public TaskServiceImpl() {
        repository = new FileTaskRepository();
        tasks = repository.loadTasks();
        nextId = tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0) + 1;
    }

    @Override
    public void addTask(String description) {
        Task task = new Task(nextId++, description );
        tasks.add(task);
        repository.saveTasks(tasks);
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public boolean markTaskAsDone(int id) {
        for (Task task: tasks) {
            if (task.getId() == id && task.getStatus() == Status.PENDING) {
                task.markAsDone();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteTask(int id) {
        boolean removed = tasks.removeIf(task -> task.getId() == id );
        if (removed) {
            repository.saveTasks(tasks);
        }
        return removed;
    }

    @Override
    public boolean editTask(int id, String newDescription) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                // We can't set description directly, so let's add a setter:
                task.setDescription(newDescription);
                repository.saveTasks(tasks);
                return true;

            }
        }
        return false;
    }

}
