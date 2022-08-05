package br.com.vendas.exception;

public class BusinessRuleException extends RuntimeException{

    public BusinessRuleException(String message) {
        super(message);
    }

}
