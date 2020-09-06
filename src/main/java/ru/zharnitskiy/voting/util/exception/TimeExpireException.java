package ru.zharnitskiy.voting.util.exception;

public class TimeExpireException extends RuntimeException {
    public TimeExpireException(String message) {
        super(message);
    }
}
