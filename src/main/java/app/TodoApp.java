package app;


import model.Task;
import service.TaskService;
import service.TaskServiceImpl;

import java.util.List;
import java.util.Scanner;

/** This is where the To Do App is run and all the required for the service
 * to run */
public class TodoApp {
    private final TaskService taskService = new TaskServiceImpl();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new TodoApp().run();
    }
    private void run() {
        System.out.println("Welcome to your Your Java To-Do List!");

        while (true) {
            showMenu();
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> handleAddTask();
                case "2" -> handleViewTasks();
                case "3" -> handleMarkDone();
                case "4" -> handleDeleteTask();
                case "5" -> {
                    System.out.println("Exiting...");
                    return;
                }
                case "6" -> handleEditTask();
                default -> System.out.println("Invalid option. Try again.");
            }

        }
    }

    private void showMenu() {
        System.out.println("\nChoose and option:");
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Mark Task as Done");
        System.out.println("4. Delete Task");
        System.out.println("5. Exit");
        System.out.println("6. Edit Task");
        System.out.print("> ");
    }

    private void handleAddTask() {
        System.out.println("Enter task description: ");
        String description = scanner.nextLine();
        taskService.addTask(description);
        System.out.println("Task Added.");
    }

    private void handleViewTasks() {
        List<Task> tasks = taskService.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("No task found.");
        }
        System.out.println("\n Your Tasks:");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private void handleMarkDone() {
        System.out.println("Enter task ID to mark as done: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (taskService.markTaskAsDone(id)) {
            System.out.println("Task marked as done. ");
        } else {
            System.out.println("Task not found or already done.");
        }
    }

    private void handleDeleteTask() {
        System.out.println("Enter task ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (taskService.deleteTask(id)) {
            System.out.println("Task deleted.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private void handleEditTask() {
        System.out.println("Enter task ID to edit");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter new description: ");
        String newDesc = scanner.nextLine();
        if (taskService.editTask(id, newDesc)) {
            System.out.println("Task Updated");
        } else {
            System.out.println("Task not found");
        }
    }

}
