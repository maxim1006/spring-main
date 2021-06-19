package com.example.springmain.service;

import com.example.springmain.entity.TodoEntity;
import com.example.springmain.entity.UserEntity;
import com.example.springmain.exception.*;
import com.example.springmain.model.TodoModel;
import com.example.springmain.model.UserModel;
import com.example.springmain.repository.TodoRepo;
import com.example.springmain.repository.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// вся логика лежит в сервисах
@Service
public class TodoService {
    @Autowired
    private TodoRepo todoRepo;

    @Autowired
    private UserRepo userRepo;

    public TodoEntity createTodo(TodoEntity todo) throws TodoAlreadyExistException, TodoUserNotFoundException {
        Long userId = todo.getUser().getId();

        Optional<UserEntity> foundUser = userRepo.findById(userId);
        boolean isFound = foundUser.isPresent();

        if (!isFound) {
            throw new TodoUserNotFoundException("Todo User not found");
        }

        if (todoRepo.findByTitle(todo.getTitle()) != null) {
            throw new TodoAlreadyExistException("Todo already exists");
        }

        todo.setUser(foundUser.get());

        return todoRepo.save(todo);
    }

    public TodoEntity updateTodo(TodoEntity todo) throws TodoNotFoundException {
        Optional<TodoEntity> foundTodo = todoRepo.findById(todo.getId());
        boolean isFound = foundTodo.isPresent();

        if (!isFound) {
            throw new TodoNotFoundException("Todo not found");
        }

        return todoRepo.save(todo);
    }

    public TodoEntity getTodoById(Long id) throws TodoNotFoundException {
        // какие-то методы уже вствоены в todoRepo к примеру findById
        Optional<TodoEntity> foundTodo = todoRepo.findById(id);
        boolean isFound = foundTodo.isPresent();

        if (!isFound) {
            throw new TodoNotFoundException("Todo not found");
        }

        return foundTodo.get();
    }

    // тут пример с TodoModel, не хочу передавать юзера
    public TodoModel getTodoModelById(Long id) throws TodoNotFoundException {
        // какие-то методы уже вствоены в todoRepo к примеру findById
        Optional<TodoEntity> foundTodo = todoRepo.findById(id);
        boolean isFound = foundTodo.isPresent();

        if (!isFound) {
            throw new TodoNotFoundException("Todo not found");
        }

        return TodoModel.toModel(foundTodo.get());
    }

    public List<TodoEntity> getTodos() {
        // какие-то методы уже вствоены в todoRepo к примеру findById
        List<TodoEntity> foundTodos = todoRepo.findAll();

        return foundTodos;
    }

    public List<TodoModel> getTodoModels() {
        // это пример полной развертки
        List<TodoModel> foundTodos = todoRepo.findAll().stream().map((TodoEntity todoEntity) -> TodoModel.toModel(todoEntity)).collect(Collectors.toList());

        return foundTodos;
    }


    public Long deleteTodo(Long id) {
        todoRepo.deleteById(id);
        return id;
    }
}
