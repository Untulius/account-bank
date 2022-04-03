package ru.iteco.accountbank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.iteco.accountbank.model.ErrorDto;
import ru.iteco.accountbank.model.exception.BankBookExistCurrencyException;
import ru.iteco.accountbank.model.exception.BankBookNotFoundException;
import ru.iteco.accountbank.model.exception.BankBookNumberUpdateException;

@RestControllerAdvice
public class BankBookExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BankBookNotFoundException.class)
    public ErrorDto notFoundException(BankBookNotFoundException e) {
        return ErrorDto.builder()
                .status(HttpStatus.NOT_FOUND.name())
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BankBookExistCurrencyException.class)
    public ErrorDto existCurrencyException(BankBookExistCurrencyException e) {
        return ErrorDto.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BankBookNumberUpdateException.class)
    public ErrorDto updateNumberException(BankBookNumberUpdateException e) {
        return ErrorDto.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .message(e.getMessage())
                .build();
    }
}
