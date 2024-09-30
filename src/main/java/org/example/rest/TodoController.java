package org.example.rest;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.example.dao.TodoDao;
import org.example.model.Todo;
import org.example.util.ErrorResponse;

import java.util.List;

public class TodoController implements ModelDriven<Object> {
    private TodoDao todoDao = new TodoDao();
    private Todo todo = new Todo();
    private Object todos;
    private int id;
    private String responseMessage;

    public static class ErrorResponseMessage {
        private String message;

        public ErrorResponseMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    @Override
    public Object getModel() {
        return todos != null ? todos : todo;
    }

    public HttpHeaders create() throws Exception {
        try{
            todo = todoDao.saveTodo(todo);
            HttpHeaders headers = new DefaultHttpHeaders("success");
            headers.setStatus(200);
            return headers;
        } catch (ErrorResponse e){
            todos = new ErrorResponseMessage(e.getMessage());
            HttpHeaders headers = new DefaultHttpHeaders("notFound");
            headers.setStatus(404);
            return headers;
        } catch (Exception e){
            todos = new ErrorResponse("An unexpected error occurred.");
            HttpHeaders headers = new DefaultHttpHeaders("error");
            headers.setStatus(500);
            return headers;
        }
    }

    public HttpHeaders index() throws Exception {
        try{
            todos = todoDao.todoList();
            return new DefaultHttpHeaders("list").disableCaching();
        } catch (Exception e){
            HttpHeaders headers = new DefaultHttpHeaders("error");
            headers.setStatus(500);
            return headers;
        }
    }

    public HttpHeaders show(){
        try {
            todo = todoDao.getTodo(id);
            HttpHeaders headers = new DefaultHttpHeaders("success");
            headers.setStatus(200);
            return headers;
        } catch (ErrorResponse e) {
            HttpHeaders headers = new DefaultHttpHeaders("notFound");
            headers.setStatus(404);
            todos = new ErrorResponseMessage(e.getMessage());
            return headers;
        } catch (Exception e) {
            todos = new ErrorResponse("An unexpected error occurred.");
            HttpHeaders headers = new DefaultHttpHeaders("error");
            headers.setStatus(500);
            return headers;
        }
    }

    public HttpHeaders update() throws Exception {
        try{
            Todo updatedTodo = todoDao.updateTodo(todo);
            this.todo = updatedTodo;

            HttpHeaders headers = new DefaultHttpHeaders("success");
            headers.setStatus(200);
            return headers;

        } catch (ErrorResponse e){
            HttpHeaders headers = new DefaultHttpHeaders("notFound");
            headers.setStatus(404);
            todos = new ErrorResponseMessage(e.getMessage());
            return headers;
        }catch (Exception e){
            todos = new ErrorResponse("An unexpected error occurred.");
            HttpHeaders headers = new DefaultHttpHeaders("error");
            headers.setStatus(500);
            return headers;
        }

    }

    public HttpHeaders destroy() throws Exception {
        try{
            String responseMessage = todoDao.deleteTodo(id);
            todos = new ErrorResponseMessage(responseMessage);

            // Return success response
            HttpHeaders headers = new DefaultHttpHeaders("success");
            headers.setStatus(200);
            return headers;

        } catch (ErrorResponse e){
            HttpHeaders headers = new DefaultHttpHeaders("notFound");
            headers.setStatus(404);
            todos = new ErrorResponseMessage(e.getMessage());
            return headers;
        }catch (Exception e){
            todos = new ErrorResponse("An unexpected error occurred.");
            HttpHeaders headers = new DefaultHttpHeaders("error");
            headers.setStatus(500);
            return headers;
        }
    }

    // Getters and Setters for id, todo, and todos
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public Todo getTodo() {
        return todo;
    }
    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    public Object getTodos() {
        return todos;
    }
    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}