package com.example.jwtproject.exception;

import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex)
    {
        Map<String,Object> body = new HashMap<>();
        body.put("Error","User not Found");
        body.put("Message",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex)
    {
        Map<String,Object> body = new HashMap<>();
        for(FieldError fieldError: ex.getBindingResult().getFieldErrors())
        {
            body.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(Exception ex)
    {
        Map<String ,Object> body = new HashMap<>();
        body.put("Error","Bad Request");
        body.put("Message",ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex)
    {
        Map<String ,Object> body = new HashMap<>();
        body.put("Error","Internal Server Error");
        body.put("Message",ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

}
