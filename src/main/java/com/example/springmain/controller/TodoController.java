package com.example.springmain.controller;

import com.example.springmain.entity.TodoEntity;
import com.example.springmain.exception.TodoAlreadyExistException;
import com.example.springmain.exception.TodoNotFoundException;
import com.example.springmain.exception.TodoUserNotFoundException;
import com.example.springmain.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    TodoService todoService;

    @PostMapping
    public ResponseEntity createTodo(@RequestBody TodoEntity todo) {
        try {
            TodoEntity createdTodo = todoService.createTodo(todo);
            return ResponseEntity.ok(createdTodo);
        }  catch (TodoUserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }  catch (TodoAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // 400 Bad Request ошибку
            return ResponseEntity.badRequest().body("TodoController create error");
        }
    }

    @PutMapping
    public ResponseEntity updateTodo(@RequestBody TodoEntity todo) {
        try {
            TodoEntity updatedTodo = todoService.updateTodo(todo);
            return ResponseEntity.ok(updatedTodo);
        } catch (TodoNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("TodoController create error");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getTodo(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(todoService.getTodoById(id));
        } catch (TodoNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("TodoController getTodo error");
        }
    }

    @GetMapping("/models/{id}")
    public ResponseEntity getTodoModel(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(todoService.getTodoModelById(id));
        } catch (TodoNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("TodoController getTodo error");
        }
    }

    @GetMapping
    public ResponseEntity getTodos() {
        try {
            return ResponseEntity.ok(todoService.getTodos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("TodoController getTodos error");
        }
    }

    @GetMapping("/models")
    public ResponseEntity getTodosModel() {
        try {
            return ResponseEntity.ok(todoService.getTodoModels());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("TodoController getTodoModels error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodo(@PathVariable Long id) {
        try {
            Long deletedId = todoService.deleteTodo(id);
            return ResponseEntity.ok("TodoController deleteTodo todo successfully deleted " + deletedId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("TodoController deleteTodo error");
        }
    }
}
