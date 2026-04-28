package com.todo.app.Todo.application.service.impl;

import com.todo.app.Todo.application.dto.RegisterDto;
import com.todo.app.Todo.application.entity.Role;
import com.todo.app.Todo.application.entity.User;
import com.todo.app.Todo.application.exception.TodoAPIException;
import com.todo.app.Todo.application.repository.RoleRepository;
import com.todo.app.Todo.application.repository.UserRepository;
import com.todo.app.Todo.application.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public String register(RegisterDto registerDto) {
        //check username is already exists in database
        if( userRepository.existsByUsername(registerDto.getUsername())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST,"username already exists!");
        }

        //check password is already exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST,"email already exists!");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);
        userRepository.save(user);

        return "User registered successfully!";
    }
}
