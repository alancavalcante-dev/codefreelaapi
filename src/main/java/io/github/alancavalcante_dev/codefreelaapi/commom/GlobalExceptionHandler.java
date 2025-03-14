package io.github.alancavalcante_dev.codefreelaapi.commom;

import io.github.alancavalcante_dev.codefreelaapi.dto.GlobalExceptionDTO;
import io.github.alancavalcante_dev.codefreelaapi.exceptions.UsernameDuplicadoExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalExceptionDTO> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                                .toList();

        GlobalExceptionDTO exeption = Error.fieldErrors("Erro de validação de campo", HttpStatus.BAD_REQUEST.value(), errors);
        return ResponseEntity.status(exeption.getStatus()).body(exeption);
    }

    @ExceptionHandler(UsernameDuplicadoExeption.class)
    public ResponseEntity<GlobalExceptionDTO> handleUsernameDuplicateException(UsernameDuplicadoExeption ex) {
        GlobalExceptionDTO exeption = Error.usernameDuplicate(
                "Username duplicado", HttpStatus.CONFLICT.value(), List.of(ex.getMessage()));
        return ResponseEntity.status(exeption.getStatus()).body(exeption);
    }

}

