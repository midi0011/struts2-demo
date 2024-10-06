package org.example.service;

import org.example.dao.TodoDao;
import org.example.model.Todo;

import java.sql.SQLException;
import java.util.List;

public class TodoServiceImpl implements TodoService {
    private TodoDao todoDao = new TodoDao();

    @Override
    public List<Todo> todoList() throws SQLException {
        return todoDao.todoList();
    }

    @Override
    public void saveTodo(Todo todo) throws SQLException {
        todoDao.saveTodo(todo);
    }

    @Override
    public Todo getTodo(int id) throws SQLException {
        return todoDao.getTodo(id);
    }

    @Override
    public void updateTodo(Todo todo) throws SQLException {
        todoDao.updateTodo(todo); // Assuming this method is implemented in TodoDao
    }

    @Override
    public void deleteTodo(int id) throws SQLException {
        todoDao.deleteTodo(id);
    }

}
