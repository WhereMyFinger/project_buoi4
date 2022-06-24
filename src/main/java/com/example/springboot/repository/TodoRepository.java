package com.example.springboot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.springboot.entities.Todo;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Integer> {

    @Query("select t from Todo t where upper(t.title) like concat('%', upper(:title), '%')")
    List<Todo> getListTodoByTitleContaining(@Param("title") String title);

    @Query("select t from Todo t where t.id = :id")
    Todo findTodoById(@Param("id") int id);
}
