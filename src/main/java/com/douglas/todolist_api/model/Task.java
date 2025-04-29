package com.douglas.todolist_api.model;

import jakarta.persistence.*;
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

    private String title;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private Status status;

}
