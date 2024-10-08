package org.example.service;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.example.dao.UserDao;
import org.example.model.Todo;
import org.example.model.User;
import org.example.util.JWTUtil;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl {

    private UserDao userDao = new UserDao();

//    @Override
//    public User findUser(String username) throws SQLException {
//        return userDao.findUser(username); // Assuming this method returns a User object
//    }

    public User authenticate(String username, String password){
        User user = userDao.findUser(username);

        if (user != null && user.getPassword().equals(password)){
            String accessToken = JWTUtil.generateAccessToken(username);
            String refreshToken = JWTUtil.generateRefreshToken(username);

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Authorization", "Bearer " + accessToken);

            ActionContext.getContext().getSession().put("user", username);
            ActionContext.getContext().getSession().put("accessToken", accessToken);
            ActionContext.getContext().getSession().put("refreshToken", refreshToken);

            return user;
        }

        return null;
    }
}
