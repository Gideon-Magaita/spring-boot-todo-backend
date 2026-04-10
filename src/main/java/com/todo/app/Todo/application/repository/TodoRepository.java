package com.todo.app.Todo.application.repository;

import com.todo.app.Todo.application.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {
}
