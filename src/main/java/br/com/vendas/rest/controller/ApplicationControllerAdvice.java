package br.com.vendas.rest.controller;

import br.com.vendas.exception.BusinessRuleException;
import br.com.vendas.exception.OrderNotFoundException;
import br.com.vendas.rest.error.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBusinessRuleException(BusinessRuleException ex){
        return new ApiErrors(HttpStatus.BAD_REQUEST.value(),"Bad Request", List.of(ex.getMessage()));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleOrderNotFoundException(OrderNotFoundException ex){
        return new ApiErrors(HttpStatus.NOT_FOUND.value(),"Not Found", List.of(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map( error -> error.getDefaultMessage() )
                .collect(Collectors.toList());


        return new ApiErrors(HttpStatus.BAD_REQUEST.value(), "Bad Request", errors);
    }
}
