package com.publishing.exception;

public class NameNotUniqueException extends Exception{
    public NameNotUniqueException() {
    }

    public NameNotUniqueException(String message) {
        super(message);
    }

    public NameNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NameNotUniqueException(Throwable cause) {
        super(cause);
    }
}
