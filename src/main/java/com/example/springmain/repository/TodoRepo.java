package com.example.springmain.repository;

import com.example.springmain.entity.TodoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepo extends CrudRepository<TodoEntity, Long> {
    TodoEntity findByTitle(String title);
    List<TodoEntity> findAll();
}
