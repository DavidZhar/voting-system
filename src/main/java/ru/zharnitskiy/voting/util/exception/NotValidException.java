package ru.zharnitskiy.voting.util.exception;

public class NotValidException extends RuntimeException {
    public NotValidException(String message) {
        super(message);
    }
}
