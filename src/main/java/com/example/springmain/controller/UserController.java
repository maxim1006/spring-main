package com.example.springmain.controller;

import com.example.springmain.entity.UserEntity;
import com.example.springmain.exception.UserAlreadyExistException;
import com.example.springmain.exception.UserNotFoundException;
import com.example.springmain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// указываю что это рест
@RestController
// запросы которые обрабатывает этот контроллер должны начинаться с url users
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    // всю логику должен хранить в соответствующих сервисах
    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok("UserController registration user is successfully saved");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
//            обрабатываем  400 Bad Request ошибку
            return ResponseEntity.badRequest().body("UserController registration error");
        }
    }

    @GetMapping
    public ResponseEntity getUsers() {
        try {
            return ResponseEntity.ok(userService.getUsers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("UserController getUsers error");
        }
    }

    @GetMapping("/models")
    public ResponseEntity getUserModels() {
        try {
            return ResponseEntity.ok(userService.getUserModels());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("UserController getUserModels error");
        }
    }

    @GetMapping("/single")
    public ResponseEntity getOneUser(@RequestParam(required = false) Long id) {
        if (id == null) {
            try {
                return ResponseEntity.ok(userService.getUsers());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("UserController getOneUser error");
            }
        } else {
            try {
                return ResponseEntity.ok(userService.getUserById(id));
            } catch (UserNotFoundException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (Exception e) {
//            обрабатываем  400 Bad Request ошибку
                return ResponseEntity.badRequest().body("UserController getOneUser error");
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            Long deletedId = userService.deleteUser(id);
            return ResponseEntity.ok("UserController deleteUser user successfully deleted " + deletedId);
        } catch (Exception e) {
//            обрабатываем  400 Bad Request ошибку
            return ResponseEntity.badRequest().body("UserController deleteUser error");
        }
    }
}
