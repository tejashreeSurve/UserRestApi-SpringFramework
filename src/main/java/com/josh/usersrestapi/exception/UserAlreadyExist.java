package com.josh.usersrestapi.exception;

/**
 * @author Tejashree Surve
 * @Purpose Handling Exception for UserAlreadyExist.
 */
public class UserAlreadyExist extends RuntimeException{
    public UserAlreadyExist(String message){
        super(message);
    }
}
