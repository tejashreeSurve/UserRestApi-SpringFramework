package com.josh.usersrestapi.exception;

/**
 * @author Tejashree Surve
 * @Purpose Handling Exception for UserDoesNotExist.
 */
public class UserDoesNotExist extends RuntimeException{
    public UserDoesNotExist(String message){
        super(message);
    }
}
