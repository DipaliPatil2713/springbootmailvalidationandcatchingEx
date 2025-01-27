package com.fullstack.validator;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomValidator extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        return super.handleMethodArgumentNotValid(ex, headers, status, request);

        Map<String ,String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            String fieldError =((FieldError)objectError).getField();

            String fieldMessage = objectError.getDefaultMessage();

            errors.put(fieldError,fieldMessage);

        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
