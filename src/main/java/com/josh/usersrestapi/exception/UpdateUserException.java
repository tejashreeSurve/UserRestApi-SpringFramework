package com.josh.usersrestapi.exception;

/**
 * @author Tejashree Surve
 * @Purpose Handling Exception occur while User Update operation.
 */
public class UpdateUserException extends RuntimeException {
    public UpdateUserException(String message){
        super(message);
    }
}
