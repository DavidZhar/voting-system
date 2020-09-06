package ru.zharnitskiy.voting.web;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.util.exception.ErrorInfo;
import ru.zharnitskiy.voting.util.exception.NotFoundException;
import ru.zharnitskiy.voting.util.exception.NotValidException;
import ru.zharnitskiy.voting.util.exception.TimeExpireException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionInfoHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ErrorInfo notFound(HttpServletRequest req, Exception e) {
        return new ErrorInfo(req.getRequestURL(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        return new ErrorInfo(req.getRequestURL(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotValidException.class)
    @ResponseBody
    public ErrorInfo invalid(HttpServletRequest req, NotValidException e) {
        return new ErrorInfo(req.getRequestURL(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorInfo invalid(HttpServletRequest req, MethodArgumentNotValidException e) {
        return new ErrorInfo(req.getRequestURL(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TimeExpireException.class)
    @ResponseBody
    public ErrorInfo timeExpire(HttpServletRequest req, TimeExpireException e) {
        return new ErrorInfo(req.getRequestURL(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return new ErrorInfo(req.getRequestURL(), e.getMessage());
    }
}