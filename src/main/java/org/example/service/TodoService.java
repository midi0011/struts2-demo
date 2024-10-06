package org.example.service;

import org.example.model.Todo;

import java.sql.SQLException;
import java.util.List;

public interface TodoService {
    List<Todo> todoList() throws SQLException;
    void saveTodo(Todo todo) throws SQLException;
    Todo getTodo(int id) throws SQLException;
    void updateTodo(Todo todo) throws SQLException;
    void deleteTodo(int id) throws SQLException;
}
