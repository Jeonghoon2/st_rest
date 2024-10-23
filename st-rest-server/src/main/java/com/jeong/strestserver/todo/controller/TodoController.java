package com.jeong.strestserver.todo.controller;

import com.jeong.strestserver.todo.dto.TodoRequestDto;
import com.jeong.strestserver.todo.dto.TodoResponseDto;
import com.jeong.strestserver.todo.entity.TodoEntity;
import com.jeong.strestserver.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
@Slf4j
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/create")
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoRequestDto requestDto) {

        log.info("requestDto: {}", requestDto.toString());

        TodoResponseDto todo = todoService.createTodo(requestDto);
        return ResponseEntity.ok().body(todo);
    }

    /* http://localhost:8080/api/v1/todo/get/all */
    @GetMapping("/get/all")
    public ResponseEntity<List<TodoEntity>> findByAll(){
        List<TodoEntity> all = todoService.findAll();
        return ResponseEntity.ok().body(all);
    }


    /* http://localhost:8080/update/complete?id=1&check=true */
    @PutMapping("/update/complete/{id}/{completed}")
    public ResponseEntity<TodoEntity> updateComplete(
            @PathVariable("id") Integer id,
            @PathVariable("completed") Boolean completed
            ) {
        TodoEntity todoEntity = todoService.updateComplete(id, completed);
        return ResponseEntity.ok().body(todoEntity);
    }
}
