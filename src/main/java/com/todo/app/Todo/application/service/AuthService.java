package com.todo.app.Todo.application.service;

import com.todo.app.Todo.application.dto.LoginDto;
import com.todo.app.Todo.application.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
