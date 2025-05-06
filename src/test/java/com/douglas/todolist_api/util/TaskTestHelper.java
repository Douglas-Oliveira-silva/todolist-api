package com.douglas.todolist_api.util;

import com.douglas.todolist_api.model.Status;
import com.douglas.todolist_api.model.Task;

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


}
