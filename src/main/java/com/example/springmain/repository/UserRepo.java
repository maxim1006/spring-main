package com.example.springmain.repository;

import com.example.springmain.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// Это интерфейс, в репо работаю с базой данных
// с помощью репозиториев изменяю/создаю данные в бд
// в CrudRepository указываю тип сущности и тип идентификатора (id)
public interface UserRepo extends CrudRepository<UserEntity, Long> {
    // метод важно называть правильно, к примеру поиск по Username - findByUsername
    UserEntity findByUsername(String username);
    List<UserEntity> findAll();
}
