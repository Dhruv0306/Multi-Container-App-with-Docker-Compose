package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    // Constructor injection for the repository dependency
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Return all tasks from the database
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Find a single task by ID, throw if not found
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    // Save a new task to the database
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Update an existing task's fields
    public Task updateTask(Long id, Task taskDetails) {
        Task task = getTaskById(id);
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());
        return taskRepository.save(task);
    }

    // Delete a task by ID
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
}