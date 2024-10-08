package org.example.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.example.model.User;
import org.example.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction extends ActionSupport {
    private String username;
    private String password;
    private UserServiceImpl userService = new UserServiceImpl();

    public String execute() {
        User user = userService.authenticate(username, password);
        if (user != null){
            return SUCCESS;
        } else  {
            addActionError("Invalid username or password");
            return INPUT;
        }
    }

    @Override
    public void validate() {
        if (username == null || username.trim().isEmpty()) {
            addFieldError("username", "Username is required");
        }

        if (password == null || password.trim().isEmpty()) {
            addFieldError("password", "Password is required");
        }
    }

    public String getUsername()  {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
