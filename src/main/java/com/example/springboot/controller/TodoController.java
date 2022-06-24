package com.example.springboot.controller;

import com.example.springboot.repository.TodoRepository;
import com.example.springboot.entities.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/todos")
    public Iterable<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    @GetMapping("/todos/{title}")
    public List<Todo> getListTodoByTitle(@PathVariable String title) {
        return todoRepository.getListTodoByTitleContaining(title);
    }

    @GetMapping("/todos/{id}")
    public Optional<Todo> getTodoById(@PathVariable int id) {
        return todoRepository.findById(id);
    }

    @PostMapping("/create")
    public String createTodo(@RequestParam String title) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todoRepository.save(todo);
        return "saved!";
    }

    @PostMapping("/update/{id}")
    public String updateTodo(@PathVariable int id, @RequestParam String title) {
        Todo todo = todoRepository.findTodoById(id);
        todo.setTitle(title);
        todoRepository.save(todo);
        return "saved!";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTodo(@PathVariable int id) {
        todoRepository.deleteById(id);
        return "deleted!";
    }
}
