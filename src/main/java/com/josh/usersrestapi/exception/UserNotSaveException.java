package com.josh.usersrestapi.exception;

/**
 * @author Tejashree Surve
 * @Purpose Handling Exception for UserNotSaveException.
 */
public class UserNotSaveException extends  RuntimeException{
    public UserNotSaveException(String message){
        super(message);
    }
}
