package org.example.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import org.example.dao.TodoDao;
import org.example.model.Todo;
import org.example.service.TodoService;
import org.example.service.TodoServiceImpl;
import org.example.util.ErrorResponse;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Conversion
public class TodoAction extends ActionSupport {

    private int id;
    private String title;
    private String description;
    private String status;
    private List<Todo> todos;
    private TodoService todoService = new TodoServiceImpl();
    private TodoDao todoDao = new TodoDao();
    private boolean isFormSubmitted = false;

    public String list() {
        try {
            todos = todoService.todoList();
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
        isFormSubmitted = true;

        if (isFormSubmitted) {
            try {
                Todo todo = new Todo();
                todo.setTitle(title);
                todo.setDescription(description);
                todo.setStatus(status);
                todoService.saveTodo(todo);
                return SUCCESS;
            } catch (ErrorResponse e) {
                addActionError(e.getMessage());
                return INPUT;
            }catch (SQLException e) {
                e.printStackTrace();
                return ERROR;
            }
        }
        return INPUT;
    }

    public String edit() {
        if (id > 0) {
            try {
                Todo todo = todoService.getTodo(id);
                if (todo != null) {
                    this.title = todo.getTitle();
                    this.description = todo.getDescription();
                    this.status = todo.getStatus();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return ERROR;
            }
        }
        return INPUT; // Return to the input form
    }

    public String update() {
        if (id > 0) {
            Todo todo = new Todo();
            todo.setId(id);
            todo.setTitle(title);
            todo.setDescription(description);
            todo.setStatus(status);

            try {
                todoService.updateTodo(todo);
                addActionMessage("Todo updated successfully!");
            } catch (ErrorResponse e) {
                e.printStackTrace();
                addActionError(e.getMessage());
                return ERROR;
            } catch (SQLException e) {
                e.printStackTrace();
                addActionError(e.getMessage());
                return ERROR;
            }
        }
        return SUCCESS;
    }

    public String delete() {
        try {
            todoService.deleteTodo(id);
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

    // Add this method in TodoAction
    public List<String> getStatusOptions() {
        return Arrays.asList("pending", "completed");
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
