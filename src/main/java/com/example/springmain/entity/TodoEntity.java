package com.example.springmain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Data
@Entity
// тут указываю название таблицы чтобы из миграции бралась правильная таблица
@Table(name = "todo")
public class TodoEntity {

    @Id
//     указываю стратегию генерации id как просто auto increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Boolean completed;

    public String description;

    // тут user именно тот который указал в mappedby в UserEntity
    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id") // не нужно
//    @JsonBackReference
    private UserEntity user;

    public TodoEntity() {
    }
}
