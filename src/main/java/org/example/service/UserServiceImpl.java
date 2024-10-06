package org.example.service;

import org.example.dao.UserDao;
import org.example.model.Todo;
import org.example.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDao();

    @Override
    public User findUser(String username) throws SQLException {
        return userDao.findUser(username); // Assuming this method returns a User object
    }
}
