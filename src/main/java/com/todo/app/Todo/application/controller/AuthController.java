package com.todo.app.Todo.application.controller;

import com.todo.app.Todo.application.dto.LoginDto;
import com.todo.app.Todo.application.dto.RegisterDto;
import com.todo.app.Todo.application.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;


    //Register REST API
    @PostMapping("/register")
    public ResponseEntity<String>register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Login REST API
    @PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody LoginDto loginDto){
        String response = authService.login(loginDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
