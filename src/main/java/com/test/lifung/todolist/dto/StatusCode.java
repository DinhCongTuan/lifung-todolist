package com.test.lifung.todolist.dto;

public enum StatusCode {
    SUCCESS(0, "Success"),
    BAD_PARAMETER(400, "Bad parameter"),
    NOT_FOUND(404,"Not found"),
    INVALID_REQUEST(411, "Invalid request"),
    INVALID_PARAM(412, "Invalid param"),
    INFO_ALREADY_USED(409, "Info is already used"),
    SERVER_ERROR(500, "Server error");


    private final int code;
    private final String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
