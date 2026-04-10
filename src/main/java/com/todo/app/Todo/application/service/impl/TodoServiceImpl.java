package com.todo.app.Todo.application.service.impl;

import com.todo.app.Todo.application.dto.TodoDto;
import com.todo.app.Todo.application.entity.Todo;
import com.todo.app.Todo.application.exception.ResourceNotFoundException;
import com.todo.app.Todo.application.repository.TodoRepository;
import com.todo.app.Todo.application.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        //convert TodoDto into Todo jpa entity
        Todo todo = modelMapper.map(todoDto,Todo.class);

        //todo jpa entity
        Todo savedTodo = todoRepository.save(todo);

        //Convert saved Todo Jpa entity object into TodoDto object
        TodoDto savedTodoDto = modelMapper.map(savedTodo,TodoDto.class);

//        return modelMapper.map(savedTodo,TodoDto.class);

        return savedTodoDto;
    }


    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo not found with id:"+id));
        return modelMapper.map(todo,TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {

        List<Todo> todos = todoRepository.findAll();

        return todos.stream().map((todo)->modelMapper.map(todo,TodoDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo not found"));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo,TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo id does not found"));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo with this id does not found"));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo,TodoDto.class);
    }

    @Override
    public TodoDto incompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Id for this todo not found"));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedtodo = todoRepository.save(todo);
        return modelMapper.map(updatedtodo,TodoDto.class);
    }
}
