package com.jeong.strestserver.todo.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TodoRequestDto {

    private String subject;
    private String body;

}
