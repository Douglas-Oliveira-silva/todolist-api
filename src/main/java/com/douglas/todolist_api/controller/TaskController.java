package com.douglas.todolist_api.controller;

import com.douglas.todolist_api.model.Status;
import com.douglas.todolist_api.model.Task;
import com.douglas.todolist_api.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation (summary = "Listas todas as tarefas", description = "Retorna uma lista co mtodas as tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public List<Task> getAllTasks(){
    return taskService.findAllTasks();
    }

        //GET / tasks {id}
    @Operation(summary = "Buscar tarefas po ID", description = "Retorna os dados de uma tarefa específica, se encontrada.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id){
            return taskService.findbyId(id);
    }

       // GET / Tasks
    @Operation(summary = "Criar uma nova tarefa", description = "Cadastra uma nova tarefa na lista.")
    @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso")
    @PostMapping
    public Task createTask(@RequestBody @Valid Task task){
           return taskService.save(task);
        }

        // DELETE / Tasks/{id}
    @Operation(summary = "Excluir tarefa", description = "Remove uma tarefa existente pelo ID.")
    @ApiResponses({
                @ApiResponse(responseCode = "204", description = "Tarefa removida com sucesso"),
                @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
        })
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
            taskService.deleteById(id);
    }

    @Operation(summary = "Atualizar tarefa", description = "Atualiza os dados de uma tarefa existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody @Valid Task task){
        return taskService.updateTask(id, task);
    }

    @Operation(summary = "Filtrar tarefas por status", description = "Retorna tarefas com base no status informado.")
    @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso")
    @GetMapping("/status")
    public List<Task> getTasksByStatus(@RequestParam Status status){
        return taskService.findByStatus(status);
    }

    @Operation(summary = "Buscar tarefas por título", description = "Retorna as tarefas que contenham o título informado.")
    @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso")
    @GetMapping("/search")
    public List<Task> getTaskByTitle(@RequestParam String title){
        return taskService.findTasksByTitle(title);
    }

    @Operation(summary = "Listar tarefas ordenadas por data de criação", description = "Ordena a lista de tarefas em ordem crescente ou decrescente.")
    @ApiResponse(responseCode = "200", description = "Lista ordenada com sucesso")
    @GetMapping ("/sorted")
    public List<Task> getAllTasksOrdered(@RequestParam(defaultValue = "asc") String direction){
        return taskService.findAllOrderedByCreatedAt(direction);
    }

    // Mock
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }


    }



