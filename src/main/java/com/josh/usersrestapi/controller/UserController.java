package com.josh.usersrestapi.controller;

import com.josh.usersrestapi.dto.UserDto;
import com.josh.usersrestapi.services.IUserServices;
import com.josh.usersrestapi.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private IUserServices userServices;
    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@Valid @RequestBody UserDto userDto){
        Response response = userServices.registerUser(userDto);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<Response> getAllUser(){
        Response response = userServices.getAllUser();
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }
}
