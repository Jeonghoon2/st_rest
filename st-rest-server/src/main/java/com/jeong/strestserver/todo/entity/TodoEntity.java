package com.jeong.strestserver.todo.entity;

import com.jeong.strestserver.todo.dto.TodoRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class TodoEntity {

    /* Todo 고유 번호*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /* 제목 */
    private String subject;

    /* 내용 */
    private String body;

    /* 완료 여부 */
    private Boolean completed;

    public TodoEntity(TodoRequestDto todoRequestDto) {
        this.subject = todoRequestDto.getSubject();
        this.body = todoRequestDto.getBody();
        this.completed = false;
    }

    public void updateComplete(Boolean completed) {
        this.completed = completed;
    }

    public TodoEntity() {}

}
