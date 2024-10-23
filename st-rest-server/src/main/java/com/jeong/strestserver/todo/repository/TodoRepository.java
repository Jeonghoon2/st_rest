package com.jeong.strestserver.todo.repository;

import com.jeong.strestserver.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Integer>{


}
