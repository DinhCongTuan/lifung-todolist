package com.test.lifung.todolist.exception;

import lombok.Data;

@Data
public class TodoListException extends RuntimeException {

    private Object data;
    public TodoListException(Object data) {
        super();
        this.data = data;
    }
    public TodoListException() {
        super();
    }
    public TodoListException(String message) {
        super(message);
    }
    public TodoListException(Throwable throwable) {
        super(throwable);
    }
    public TodoListException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
