package com.loschiferos.ztech.shared.domain.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException() {}

    public ValidationException(String message) {super(message);}
}
