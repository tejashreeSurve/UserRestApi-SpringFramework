package com.josh.usersrestapi.exception;

import com.josh.usersrestapi.utility.MessageInfo;
import com.josh.usersrestapi.utility.Response;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tejashree Surve
 * @Purpose GlobalException class for exception handling.
 */
@Configuration
@ControllerAdvice
public class GlobalException {

    /**
     * Handling Exception occur while Login operation.
     * @param e Exception.
     * @return Response object.
     */
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Response> LoginException(Exception e){
        return new ResponseEntity<Response>(
                new Response(HttpStatus.BAD_REQUEST.value(),e.getMessage(),"Please try again!!!!"),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handling Exception occur while Register operation.
     * @param e Exception.
     * @return Response object.
     */
    @ExceptionHandler(RegisterException.class)
    public ResponseEntity<Response> RegisterException(Exception e){
        return new ResponseEntity<Response>(
                new Response(HttpStatus.BAD_REQUEST.value(),e.getMessage(),"Please try again!!!!"),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handling Exception occur while User Validate operation.
     * @param e Exception.
     * @return Response object.
     */
    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<Response> ValidateException(Exception e){
        return new ResponseEntity<Response>(
                new Response(HttpStatus.BAD_REQUEST.value(),e.getMessage(),"Please try again!!!!"),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handling Exception for Invalid token.
     * @param e Exception.
     * @return Response object.
     */
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Response> InvalidTokenException(Exception e){
        return new ResponseEntity<Response>(
                new Response(HttpStatus.BAD_REQUEST.value(),e.getMessage(),"Please try again!!!!"),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handling Exception occur while User Update operation.
     * @param e Exception.
     * @return Response object.
     */
    @ExceptionHandler(UpdateUserException.class)
    public ResponseEntity<Response> UpdateUserException(Exception e){
        return new ResponseEntity<Response>(
                new Response(HttpStatus.BAD_REQUEST.value(),e.getMessage(),"Please try again!!!!"),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handling Exception for MethodArgumentNotValidException.
     * @param e Exception.
     * @return Response object.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        String stringMessage = errors.toString();
        return new ResponseEntity<Response>(
                new Response(HttpStatus.BAD_REQUEST.value(),stringMessage,"Please try again!!!!"),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handling Exception for UserNotSaveException.
     * @param e Exception.
     * @return Response object.
     */
    @ExceptionHandler(UserNotSaveException.class)
    public ResponseEntity<Response> UserNotSaveException(Exception e){
        return new ResponseEntity<Response>(
                new Response(HttpStatus.BAD_REQUEST.value(),e.getMessage(),"Please try again!!!!"),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handling Exception for ListNotExistException.
     * @param e Exception.
     * @return Response object.
     */
    @ExceptionHandler(ListNotExistException.class)
    public ResponseEntity<Response> ListNotExistException(Exception e){
        return new ResponseEntity<Response>(
                new Response(HttpStatus.BAD_REQUEST.value(),e.getMessage(),"Please try again!!!!"),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handling Exception for UnauthorizedAccessException.
     * @param e Exception.
     * @return Response object.
     */
    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<Response> UnauthorizedAccessException(Exception e){
        return new ResponseEntity<Response>(
                new Response(HttpStatus.UNAUTHORIZED.value(),e.getMessage(),"Please try again!!!!"),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handling Exception for HttpMessageNotReadableException.
     * @param e Exception.
     * @return Response object.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> MessageNotReadableException(HttpMessageNotReadableException e){
        return new ResponseEntity<Response>(
                new Response(HttpStatus.BAD_REQUEST.value(),"Please Enter Valid Patterns=yyyy-MM-dd","Please try again!!!!"),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handling Exception for UserDoesNotExist.
     * @param e Exception.
     * @return Response object.
     */
    @ExceptionHandler(UserDoesNotExist.class)
    public ResponseEntity<Response> UserDoesNotExist(Exception e){
        return new ResponseEntity<Response>(
                new Response(HttpStatus.BAD_REQUEST.value(),e.getMessage(),"Please try again!!!!"),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Handling Exception for UserAlreadyExist.
     * @param e Exception.
     * @return Response object.
     */
    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<Response> UserAlreadyExist(Exception e){
        return new ResponseEntity<Response>(
                new Response(HttpStatus.BAD_REQUEST.value(),e.getMessage(),"Please try again!!!!"),
                HttpStatus.BAD_REQUEST);
    }
}
