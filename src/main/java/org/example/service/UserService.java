package org.example.service;

import org.example.model.Todo;
import org.example.model.User;

import java.sql.SQLException;

public interface UserService {
    User findUser(String username) throws SQLException;
}
