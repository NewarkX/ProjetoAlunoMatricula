package com.sistema.controllers;

import com.sistema.exception.Error;
import com.sistema.exception.NotFoundException;

import com.sistema.exception.PropertyError;
import com.sistema.exception.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Calendar;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity erroPadrao(Exception e, HttpServletRequest request){
        Error erro = new Error(Calendar.getInstance(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity erroValidacao(ConstraintViolationException e, HttpServletRequest request){
        ValidationError erro = new ValidationError(
                Calendar.getInstance(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY.name(),
                "Erro de validacao",
                request.getRequestURI());
                for (ConstraintViolation cv : e.getConstraintViolations()){
                    PropertyError p = new PropertyError(cv.getPropertyPath().toString(), cv.getMessage());
                    erro.getErrors().add(p);
                }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity erroNaoEncontrado(NotFoundException e, HttpServletRequest request){
        Error erro = new Error(Calendar.getInstance(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}
