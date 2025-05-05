package service;

import model.Task;

import java.util.List;

/** A simple interface to implement the task service*/
public interface TaskService {
    void addTask(String description);
    List<Task> getAllTasks();
    boolean markTaskAsDone(int id);
    boolean deleteTask (int id);
    boolean editTask(int id, String newDescription);

}
