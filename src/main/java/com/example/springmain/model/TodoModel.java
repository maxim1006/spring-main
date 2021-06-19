package com.example.springmain.model;

import com.example.springmain.entity.TodoEntity;
import lombok.Data;

@Data
public class TodoModel {
    private Long id;
    private String title;
    private Boolean completed;

    public static TodoModel toModel(TodoEntity entity) {
        TodoModel model = new TodoModel();

        model.setId(entity.getId());
        model.setCompleted(entity.getCompleted());
        model.setTitle(entity.getTitle());

        return model;
    }
}
