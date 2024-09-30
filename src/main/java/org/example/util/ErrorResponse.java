package org.example.util;

public class ErrorResponse extends RuntimeException {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
