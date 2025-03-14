package io.github.alancavalcante_dev.codefreelaapi.commom;

import io.github.alancavalcante_dev.codefreelaapi.dto.GlobalExceptionDTO;

import java.util.List;

public class Error {


    public static GlobalExceptionDTO fieldErrors(String message, int status, List<String> errors) {
        return new GlobalExceptionDTO(message, status, errors);
    }
}
