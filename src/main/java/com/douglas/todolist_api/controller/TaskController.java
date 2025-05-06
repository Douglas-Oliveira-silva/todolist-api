package com.douglas.todolist_api.controller;

import com.douglas.todolist_api.model.Status;
import com.douglas.todolist_api.model.Task;
import com.douglas.todolist_api.service.TaskService;
import jakarta.validation.Valid;
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
    public Task createTask(@RequestBody @Valid Task task){
           return taskService.save(task);
        }
        // DELETE / Tasks/{id}
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
            taskService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody @Valid Task task){
        return taskService.updateTask(id, task);
    }

    @GetMapping("/status")
    public List<Task> getTasksByStatus(@RequestParam Status status){
        return taskService.findByStatus(status);
    }

    @GetMapping("/search")
    public List<Task> getTaskByTitle(@RequestParam String title){
        return taskService.findTasksByTitle(title);
    }
    @GetMapping ("/sorted")
    public List<Task> getAllTasksOrdered(@RequestParam(defaultValue = "asc") String direction){
        return taskService.findAllOrderedByCreatedAt(direction);
    }

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }


    }



