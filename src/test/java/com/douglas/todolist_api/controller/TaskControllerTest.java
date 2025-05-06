package com.douglas.todolist_api.controller;

import com.douglas.todolist_api.model.Status;
import com.douglas.todolist_api.model.Task;
import com.douglas.todolist_api.repository.TaskRepository;
import com.douglas.todolist_api.service.TaskService;
import com.douglas.todolist_api.util.TaskTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class TaskControllerTest {

    private TaskRepository taskRepository;
    private TaskService taskService;
    private TaskController taskController;

    @BeforeEach

    void setUp(){
        // Cria um mock do repositório
        taskRepository = Mockito.mock(TaskRepository.class);

        // Cria a camada de serviço usando o repositório mockado
        taskService = new TaskService(taskRepository);

        // Cria o controller passado o service mockado
        taskController = new TaskController(taskService);
    }

    List<Task> mockTasks = Arrays.asList(
            TaskTestHelper.createTask1(),
            TaskTestHelper.createTask2()
    );


}
