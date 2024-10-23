package com.jeong.strestserver.todo.dto;

import com.jeong.strestserver.todo.entity.TodoEntity;
import lombok.Data;

@Data
public class TodoResponseDto {

    private Integer id;
    private String subject;
    private String body;
    private Boolean completed;

    public TodoResponseDto(TodoEntity todo) {
        this.id = todo.getId();
        this.subject = todo.getSubject();
        this.body = todo.getBody();
        this.completed = todo.getCompleted();
    }
}
