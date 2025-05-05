package com.douglas.todolist_api.repository;

import com.douglas.todolist_api.model.Status;
import com.douglas.todolist_api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
    public interface TaskRepository extends JpaRepository<Task, Long>{
        List<Task> findByStatus(Status status);

    }
