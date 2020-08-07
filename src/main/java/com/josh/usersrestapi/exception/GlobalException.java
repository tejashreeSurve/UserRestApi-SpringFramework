package com.josh.usersrestapi.exception;

import com.josh.usersrestapi.utility.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalException {
    @Autowired
    MessageInfo message;
}
