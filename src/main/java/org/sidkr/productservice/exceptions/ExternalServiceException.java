package org.sidkr.productservice.exceptions;

public class ExternalServiceException extends RuntimeException{

    private final String message;

    public ExternalServiceException(String message){
        super(message);
        this.message = message;
    }

    public ExternalServiceException(String message, Throwable cause){
        super(message, cause);
        this.message = message;
    }

    public String getErrorMessage(){
        return this.message;
    }
}
