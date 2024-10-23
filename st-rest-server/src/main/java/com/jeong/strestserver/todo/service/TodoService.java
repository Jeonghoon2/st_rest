package com.jeong.strestserver.todo.service;

import com.jeong.strestserver.todo.dto.TodoRequestDto;
import com.jeong.strestserver.todo.dto.TodoResponseDto;
import com.jeong.strestserver.todo.entity.TodoEntity;
import com.jeong.strestserver.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;

    /* Todo 저장 */
    @Transactional
    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        /* TodoEntity 형태의 Todo 만들기 */
        TodoEntity newTodo = new TodoEntity(requestDto);

        /* 실제 데이터베이스에 저장 */
        TodoEntity save = todoRepository.save(newTodo);

        /* TodoEntity를 TodoResponse 형태로 변환해서 반환 */
        return new TodoResponseDto(save);
    }

    /*
    * Todo 모두 조회
    * todoRepository.findAll() = select * from todo
    * */
    public List<TodoEntity> findAll() {
        return todoRepository.findAll();
    }

    /**
     * Todo 수정
     * todoRepository.findById(id) = select * from todo where id = 1;
     * */
    @Transactional
    public TodoEntity updateComplete(Integer id, Boolean completed) {
        Optional<TodoEntity> findById = todoRepository.findById(id);

        TodoEntity todoEntity = findById.get();
        todoEntity.updateComplete(completed);
        return todoEntity;
    }

    /*
    * Todo 삭제
    * todoRepository.delete(todoEntity) = delete from todo where id = 1;
    * */
    public void deleteTodo(Integer id) {

        /* 삭제할 Todo 찾기 */
        TodoEntity todoEntity = todoRepository.findById(id).get();

        todoRepository.delete(todoEntity);
    }
}
