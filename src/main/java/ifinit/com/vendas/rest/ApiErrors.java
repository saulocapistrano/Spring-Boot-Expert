package ifinit.com.vendas.rest;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {
    @Getter
    List<String> errors;


    public ApiErrors(String messageError) {
        this.errors = Arrays.asList(messageError);
    }
}
