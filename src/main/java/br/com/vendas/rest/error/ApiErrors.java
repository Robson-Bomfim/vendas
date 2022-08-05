package br.com.vendas.rest.error;

import lombok.Getter;
import java.util.List;
import java.util.Arrays;


public class ApiErrors {
    @Getter
    private Integer status;

    @Getter
    private String message;

    @Getter
    private List<String> errors;

    public ApiErrors(Integer status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiErrors(String errorMessage) {
        this.errors = Arrays.asList(errorMessage);
    }

}
