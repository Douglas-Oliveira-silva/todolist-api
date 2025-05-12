package com.douglas.todolist_api.controller;

import com.douglas.todolist_api.model.Status;
import com.douglas.todolist_api.model.Task;
import com.douglas.todolist_api.repository.TaskRepository;
import com.douglas.todolist_api.service.TaskService;
import com.douglas.todolist_api.util.TaskTestHelper;
import org.junit.jupiter.api.BeforeEach; // JUnit: roda antes de cada teste
import org.junit.jupiter.api.Test; //JUnit: define que é um metodo de teste
import org.mockito.Mockito; // Mockito: biblioteca para simular comportamentos
import static org.junit.jupiter.api.Assertions.*; // Métodos para verificar os resultados (assertivas)

import java.util.List;

public class TaskControllerTest {

    // dependências da aplicação
    private TaskRepository taskRepository;
    private TaskService taskService;
    private TaskController taskController;

    @BeforeEach

    void setUp(){
        // Criamos um mock (simulação) do repositório de tarefas usando o Mockito
        taskRepository = Mockito.mock(TaskRepository.class);

        // Criamos o serviço de tarefas, injetando o repositório mockado
        taskService = new TaskService(taskRepository);

        // Criamos o controller de tarefas, injetando o service real (mas com dependência mockada)
        taskController = new TaskController(taskService);

       /* List<Task> mockTasks = Arrays.asList(
                TaskTestHelper.createTask1(),
                TaskTestHelper.createTask2()
        );**/
    }



    @Test
    void shouldReturnListOfTasks(){
        // Arrange (Preparação): Criamos uma lista simulada de tarefas
        List<Task> mockTasks = TaskTestHelper.createSampleTasks();

        // Simulamos o comportamento do repositório: quando findAll for chamado, retorna a lista simulada
        Mockito.when(taskRepository.findAll()).thenReturn(mockTasks);

        // Act (Ação): Chamamos o metodo que queremos testar
        List<Task> result = taskController.getAllTasks();

        // Assert (Verificação): Comparamos o resultado com o esperado
        assertEquals(mockTasks.size(), result.size()); // verifica se retornou a mesma quantidade
        assertEquals(mockTasks.get(0).getTitle(), result.get(0).getTitle()); // verifica o primeiro título

    }
    @Test
    void shouldCreateTaskSuccessfully(){
        // Arrange: Criamos uma nova tarefa (sem ID) e uma tarefa simulando o retorno do banco (com ID)
        Task newTask = new Task(null, "Estudar teste","Estudar como criar testes unitário", Status.PENDENTE);
        Task savedTask = new Task(1L,"Estudar testes", "Estudar como criar testes unitário", Status.PENDENTE);

        // Simulamos que, ao salvar a newTask, o repositório retornará a savedTask
        Mockito.when(taskRepository.save(newTask)).thenReturn(savedTask);

       // Act: Chamamos o método de criação do controller
       Task result = taskController.createTask(newTask);

        // Assert: Verificamos se os dados retornados estão corretos
        assertNotNull(result.getId()); //Verifica se a tarefa foi salva com um ID
        assertEquals(savedTask.getTitle(),result.getTitle()); //Verifica o título
        assertEquals(savedTask.getDescription(),result.getDescription()); // Verifica a descrição
        assertEquals(Status.PENDENTE,result.getStatus()); // Verifica o status
    }


}
