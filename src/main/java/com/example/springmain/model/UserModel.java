package com.example.springmain.model;

import com.example.springmain.entity.TodoEntity;
import com.example.springmain.entity.UserEntity;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

// тут указываю только те поля которые будут использоваться на клиенте, те если хочу отправлять не всю дату а только ту которую нужно
@Data
public class UserModel {
    private Long id;
    private String username;
    private List<TodoEntity> todos;
    private List<TodoModel> todoModels;

    // тут описываю что должна возвращать функция из UserEntity, а дальше в сервисе вместо UserEntity возвращаемого из getById меняю на UserModel
    public static UserModel toModel(UserEntity entity) {
        UserModel model = new UserModel();

        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setTodos(entity.getTodos());
        // прикольный пример итерации по TodoModel item с применением toModel метода
        model.setTodoModels(entity.getTodos().stream().map(TodoModel::toModel).collect(Collectors.toList()));

        return model;
    }
}
