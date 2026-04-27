package com.todo.app.Todo.application.repository;

import com.todo.app.Todo.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User>findByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<User>findByUsernameOrEmail(String username,String email);
}
