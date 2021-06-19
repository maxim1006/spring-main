package com.example.springmain.service;

import com.example.springmain.entity.UserEntity;
import com.example.springmain.exception.UserAlreadyExistException;
import com.example.springmain.exception.UserNotFoundException;
import com.example.springmain.model.UserModel;
import com.example.springmain.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// вся логика лежит в сервисах
@Service
public class UserService {
    // делаю инъекцию userRepo в UserController
    @Autowired
    private UserRepo userRepo;

    public List<UserEntity> getUsers() {
        List<UserEntity> users = userRepo.findAll();

        return users;
    }

    public List<UserModel> getUserModels() {
        List<UserModel> users = userRepo.findAll().stream().map((UserEntity user) -> UserModel.toModel(user)).collect(Collectors.toList());

        return users;
    }

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("User already exists");
        }

        return userRepo.save(user);
    }

    public UserModel getUserById(Long id) throws UserNotFoundException {
        // оказывается какие-то методы уже вствоены в userRepo к примеру findById
        Optional<UserEntity> foundUser = userRepo.findById(id);
        boolean isFound = foundUser.isPresent();

        if (!isFound) {
            throw new UserNotFoundException("User not found");
        }

        return UserModel.toModel(foundUser.get());
    }

    public Long deleteUser(Long id) throws UserAlreadyExistException {
        userRepo.deleteById(id);
        return id;
    }
}
