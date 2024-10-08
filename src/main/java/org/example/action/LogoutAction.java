package org.example.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {
    public String execute() {
        // Clear the session
        ActionContext.getContext().getSession().clear();
        return SUCCESS; // Redirect to login page
    }
}
