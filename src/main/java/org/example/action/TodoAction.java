package org.example.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import org.example.dao.TodoDao;
import org.example.model.Todo;

import java.sql.SQLException;
import java.util.List;

@Conversion
public class TodoAction extends ActionSupport {

    private int id;
    private String title;
    private String description;
    private String status;
    private List<Todo> todos;
    private TodoDao todoDao = new TodoDao();

    public String list() {
        try {
            todos = todoDao.todoList();
            System.out.println(todos);  // Check if this prints the list
            for (Todo todo : todos) {
                System.out.println(todo.getTitle()); // Print each title
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String add(){
        if (title != null && description != null && status !=null){
            Todo todo = new Todo();
            todo.setTitle(title);
            todo.setDescription(description);
            todo.setStatus(status);
            try {
                todoDao.saveTodo(todo);
            } catch (SQLException e){
                e.printStackTrace();
                return ERROR;
            }
            return SUCCESS;
        }
        return INPUT;
    }

    public String edit(){
        if (id > 0){
            try {
                Todo todo = todoDao.getTodo(id);
                if (todo != null){
                    todo.setTitle(title);
                    todo.setDescription(description);
                    todo.setStatus(status);
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return INPUT;
    }

    public String delete() {
        try {
            todoDao.deleteTodo(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String testConnection() {
        boolean isConnected = todoDao.testConnection();
        if (isConnected) {
            return "success";
        } else {
            return "error";
        }
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

    public List<Todo> getTodos() {
        return todos;
    }
}
