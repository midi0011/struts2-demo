package org.example.dao;

import org.example.model.Todo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {
    private Connection getConnection() throws SQLException{
        String jdbcUrl = "jdbc:mysql://localhost:3306/teststruts2";
        String jdbcName = "root";
        String jdbcPassword = "asdas-123";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(jdbcUrl, jdbcName, jdbcPassword);
    }

    public boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn.isValid(0); // 0 is the timeout in seconds
        } catch (SQLException e) {
            return false;
        }
    }


    public void saveTodo(Todo todo) throws SQLException{
        String sql = "INSERT INTO todolist (title, description, status) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, todo.getTitle());
            pstmt.setString(2, todo.getDescription());
            pstmt.setString(3, todo.getStatus());
            pstmt.executeUpdate();
        }
    }

    public List<Todo> todoList() throws SQLException{
        List<Todo> todos = new ArrayList<>();
        String sql = "SELECT * FROM todolist";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                Todo todo = new Todo();
                todo.setId(rs.getInt("id"));
                todo.setTitle(rs.getString("title"));
                todo.setDescription(rs.getString("description"));
                todo.setStatus(rs.getString("status"));
                todos.add(todo);
            }
            return todos;
        }
    }

    public Todo getTodo(int id) throws SQLException{
        String sql = "SELECT * FROM todolist WHERE id=?";
        Todo todo = null;
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    todo = new Todo();
                    todo.setId(rs.getInt("id"));
                    todo.setTitle(rs.getString("title"));
                    todo.setDescription(rs.getString("description"));
                    todo.setStatus(rs.getString("status"));
                }
            }
        }
        return todo;
    }

    public void updateTodo(Todo todo) throws SQLException{
        String sql = "UPDATE todolist SET title=?, description=?, status=? WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, todo.getTitle());
            pstmt.setString(2, todo.getDescription());
            pstmt.setString(3, todo.getStatus());
            pstmt.setInt(4, todo.getId());

            pstmt.executeUpdate();
        }
    }

    public void deleteTodo(int id) throws SQLException{
        String sql = "DELETE FROM todolist WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
