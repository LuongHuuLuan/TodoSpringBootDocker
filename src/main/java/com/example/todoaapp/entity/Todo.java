package com.example.todoaapp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String timeStart;
    @Column
    private String timeEnd;
    @Column
    private String description;
}
