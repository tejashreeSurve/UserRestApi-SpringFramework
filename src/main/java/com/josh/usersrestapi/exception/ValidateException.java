package com.josh.usersrestapi.exception;

/**
 * @author Tejashree Surve
 * @Purpose Handling Exception occur while User Validate operation.
 */
public class ValidateException extends RuntimeException {
    public ValidateException(String message){
        super(message);
    }
}
