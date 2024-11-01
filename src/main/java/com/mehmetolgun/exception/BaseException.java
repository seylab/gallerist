package com.mehmetolgun.exception;

public class BaseException extends RuntimeException {

    public BaseException(ErrorMessage errorMessage) {
        super(errorMessage.prepareErrorMessage());
    }
}
