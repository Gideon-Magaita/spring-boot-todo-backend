package com.todo.app.Todo.application.repository;

import com.todo.app.Todo.application.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
