package ifinit.com.vendas.rest.controller;

import ifinit.com.vendas.exception.OrderedNotFoundException;
import ifinit.com.vendas.exception.RulerManagerException;
import ifinit.com.vendas.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RulerManagerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRulerManagerException(RulerManagerException exception){
        String messageError = exception.getMessage();
        return new ApiErrors(messageError);
    }

    @ExceptionHandler(OrderedNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleOrderedNotFoundException(OrderedNotFoundException exception){
    return new ApiErrors(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException exception){
        final List<String> errors = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(errors);

    }
}
