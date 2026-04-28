package com.todo.app.Todo.application.service;

import com.todo.app.Todo.application.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
}
