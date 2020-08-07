package com.josh.usersrestapi.controller;

import com.josh.usersrestapi.dto.EditUserDto;
import com.josh.usersrestapi.dto.LoginDto;
import com.josh.usersrestapi.dto.UserDto;
import com.josh.usersrestapi.services.IUserServices;
import com.josh.usersrestapi.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * @author Tejashree Surve
 * @Purpose : This is RestApi Controller for User Operation.
 */
@RestController
public class UserController {
    @Autowired
    private IUserServices userServices;

    // Register User Rest Api
    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@Valid @RequestBody UserDto userDto){
        Response response = userServices.registerUser(userDto);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    // Get All User Rest Api
    @GetMapping("/getAllUser/{token}")
    public ResponseEntity<Response> getAllUser(@PathVariable String token){
        Response response = userServices.getAllUser(token);
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    // Login Rest Api
    @PostMapping("/login")
    public ResponseEntity<Response> login(@Valid @RequestBody LoginDto loginDto){
        Response response = userServices.login(loginDto);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    // Update User Rest Api
    @PostMapping("/updateUser/{token}")
    public ResponseEntity<Response> updateUser(@PathVariable String token, @Valid @RequestBody EditUserDto editUserDto){
        Response response = userServices.updateUser(token,editUserDto);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    // Validate User Rest Api
    @GetMapping("/validateUser/{token}")
    public ResponseEntity<Response> validateUser(@PathVariable String token){
        Response response = userServices.validateUser(token);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    // Logout Rest Api
    @GetMapping("/logout/{token}")
    public ResponseEntity<Response> logout(@PathVariable String token){
        Response response = userServices.logout(token);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }
}
