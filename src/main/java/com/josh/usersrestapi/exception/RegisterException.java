package com.josh.usersrestapi.exception;

/**
 * @author Tejashree Surve
 * @Purpose Handling Exception occur while Register operation.
 */
public class RegisterException extends RuntimeException {
    public RegisterException(String message){
        super(message);
    }
}
