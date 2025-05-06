package com.douglas.todolist_api.util;

import com.douglas.todolist_api.model.Status;
import com.douglas.todolist_api.model.Task;

import java.util.Arrays;
import java.util.List;

public class TaskTestHelper {

    // task exemplo 1: estudar JUnit
    public static Task createTask1(){
        Task task = new Task();
        task.setId(1L);
        task.setDescription("Estudar testes unitários com JUnit");
        task.setStatus(Status.PENDENTE);
        return task;
    }

   // task exemplo 2: Implementar filtro
   public static Task createTask2(){
        Task task = new Task();
        task.setId(2L);
        task.setTitle("Criar filtro de ordenação por data");
        task.setStatus(Status.EM_PROGRESSO);
        return task;
   }

   public static List<Task> createSampleTasks(){
        Task task1 = new Task(1L, "Estudar Junit","estudar testes unitários com JUnit", Status.PENDENTE);
        Task task2 = new Task(2L, "Implementar filtro", "Criar filtro de ordenação por data", Status.EM_PROGRESSO);
        return Arrays.asList(task1, task2);
   }


}
