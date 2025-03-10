package io.github.alancavalcante_dev.codefreelaapi.exceptionhandler;

import io.github.alancavalcante_dev.codefreelaapi.exceptions.UsernameDuplicadoExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Erro de validação de campo: " + ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(UsernameDuplicadoExeption.class)
    public ResponseEntity<String> handleUsernameDuplicadoExeption(UsernameDuplicadoExeption ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Erro:" + ex.getMessage());
    }

}

