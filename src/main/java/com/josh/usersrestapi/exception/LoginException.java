package com.josh.usersrestapi.exception;

/**
 * @author Tejashree Surve
 * @Purpose Handling Exception occur while Login operation.
 */
public class LoginException extends RuntimeException {
    public LoginException(String message){
        super(message);
    }
}
