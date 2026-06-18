package com.example.taskmanager.repository;

import com.example.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data JPA generates all CRUD methods automatically
public interface TaskRepository extends JpaRepository<Task, Long> {
}