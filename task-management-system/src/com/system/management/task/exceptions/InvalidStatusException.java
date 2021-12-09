package com.system.management.task.exceptions;

public class InvalidStatusException extends IllegalArgumentException {
    public InvalidStatusException(String msg) {
        super(msg);
    }
}
