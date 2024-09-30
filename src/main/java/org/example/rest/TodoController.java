package org.example.rest;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.example.dao.TodoDao;
import org.example.model.Todo;

import java.sql.SQLException;
import java.util.Map;

public class TodoController implements ModelDriven<Object>, ParameterAware {
    private int id;
    private String title;
    private String description;
    private String status;
    private Object model;
    private TodoDao todoDao = new TodoDao();
    private Map<String, String[]> parameters;

    // GET	/api/todos
    public HttpHeaders index() {
        try {
            model = todoDao.todoList();
            System.out.println(model);  // Check if this prints the list
        } catch (SQLException e) {
            e.printStackTrace();
            HttpHeaders headers = new DefaultHttpHeaders("error");
            headers.setStatus(500);
            return headers;
        }
        System.out.println("GET \t /user");
        HttpHeaders headers = new DefaultHttpHeaders("success");
        headers.setStatus(200);
        return headers;
    }

    // GET /api/todos/{id}
    public HttpHeaders show(){
        try{
            model = todoDao.getTodo(id);
            System.out.println(model);
        } catch (SQLException e){
            e.printStackTrace();
            HttpHeaders headers = new DefaultHttpHeaders("error");
            headers.setStatus(500);
            return headers;
        }
        System.out.println("GET \t /user/" + id);
        return new DefaultHttpHeaders("show");
    }

    @Override
    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    // POST /api/todos
    public HttpHeaders create(){
        Todo todo = new Todo();
        todo.setTitle(parameters.get("title")[0]);
        todo.setDescription(parameters.get("description")[0]);
        todo.setStatus(parameters.get("status")[0]);

        try{
            todoDao.saveTodo(todo);
            model = todo;
        } catch (SQLException e){
            e.printStackTrace();
            HttpHeaders headers = new DefaultHttpHeaders("error");
            headers.setStatus(500);
            return headers;
        }

        System.out.println("POST \t /api/todos");
        HttpHeaders headers = new DefaultHttpHeaders("success");
        headers.setStatus(201);
        return headers;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public Object getModel() {
        return model;
    }
}
