package com.douglas.todolist_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;

/*
* @Entity: diz ao Spring que essa classe vai virar uma tabela no banco.
@Table(name = "tasks"): define o nome da tabela como "tasks".
@Id + @GeneratedValue: define o campo id como chave primária e auto incremento.
@Data, @AllArgsConstructor, @NoArgsConstructor: são do Lombok para gerar getters, setters, construtores automaticamente, deixando o código mais limpo.
* */
@Entity
@Table (name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    //Atributos
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    private String title;

    @NotBlank(message = "A descrição é obrigatória")
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status não pode ser nulo")
    private Status status;

    // construtor para testes
    public Task(Long id, String title, String description, Status status){
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
