package com.josh.usersrestapi.exception;

/**
 * @author Tejashree Surve
 * @Purpose Handling Exception for ListNotExistException.
 */
public class ListNotExistException extends RuntimeException {
    public ListNotExistException(String message){
        super(message);
    }
}
