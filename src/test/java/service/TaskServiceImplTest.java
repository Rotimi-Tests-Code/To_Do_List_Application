package service;


import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** This is the Test of the Task Service with all the required items */
public class TaskServiceImplTest {

    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        File file = new File("tasks.txt");
        if (!file.delete()) {
            System.out.println("Failed To Delete File.");
        }

        taskService = new TaskServiceImpl();
    }

    @Test
    void testAddTask() {
        taskService.addTask("Learn Java");
        List<Task> tasks = taskService.getAllTasks();
        assertEquals(1, tasks.size());
        assertEquals("Learn Java", tasks.get(0).getDescription());
    }

    @Test
    void testMarkTaskAsDone() {
        taskService.addTask("Write test");
        boolean result = taskService.markTaskAsDone(1);
        assertTrue(result);
        assertEquals("DONE", taskService.getAllTasks().get(0).getStatus().name());
    }

    @Test
    void testDeleteTask() {
        taskService.addTask("Delete me");
        boolean result = taskService.deleteTask(1);
        assertTrue(result);
        assertTrue(taskService.getAllTasks().isEmpty());
    }

    @Test
    void testEditTask() {
        taskService.addTask("Old desc");
        boolean result = taskService.editTask(1, "New Desc");
        assertTrue(result);
        assertEquals("New desc", taskService.getAllTasks().get(0).getDescription());
    }

}
