package com.douglas.todolist_api.controller;

import com.douglas.todolist_api.model.Task;
import com.douglas.todolist_api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/*
@RestController: Indica que essa classe é um controlador REST (responde com JSON, geralmente).
@RequestMapping("/tasks"): Define o endpoint base da API. Todas as rotas aqui começam com /tasks.
*/

@RestController
@RequestMapping("/tasks")
public class TaskController {

        @Autowired
        private TaskService taskService;

        //GET / tasks
    @GetMapping
    public List<Task> getAllTasks(){
    return taskService.findAllTasks();
    }

        //GET / tasks {id}
    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id){
            return taskService.findbyId(id);
    }
       // GET / Tasks
    @PostMapping
    public Task createTask(@RequestBody Task task){
           return taskService.save(task);
        }
        // DELETE / Tasks/{id}
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
            taskService.deleteById(id);
    }

    }



