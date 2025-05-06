package com.douglas.todolist_api.service;

import com.douglas.todolist_api.model.Status;
import com.douglas.todolist_api.model.Task;
import com.douglas.todolist_api.repository.TaskRepository;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


/*
@Service: Indica que esta classe é um componente de serviço no Spring, usado para conter lógica de negócios.
@Autowired: Injeta automaticamente o repositório para ser utilizado na classe.
*/

@Service
public class TaskService {

        @Autowired
        private TaskRepository taskRepository;

        public List<Task> findAllTasks(){
            return taskRepository.findAll();
    }
        public Optional<Task> findbyId(Long id) {
            return taskRepository.findById(id);
        }
        public List<Task>findByStatus(Status status){
            return taskRepository.findByStatus(status);

        }

        public Task save(Task task) {
            return taskRepository.save(task);
        }

        public void deleteById(Long id){
            taskRepository.deleteById(id);
        }

    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID: " + id));
    }

    // Metodo para buscar tarefas por título
    public List<Task> findTasksByTitle(String title){
            return taskRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Task> findAllOrderedByCreatedAt(String direction){
            Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by("createdAt").descending()
            : Sort.by("createdAt").ascending();

                return taskRepository.findAll(sort);
    }

    // para realizarmos os testes (mock)
    public TaskService(TaskRepository taskRepository){
            this.taskRepository = taskRepository;
    }


}

