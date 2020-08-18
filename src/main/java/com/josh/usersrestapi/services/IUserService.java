package com.josh.usersrestapi.services;

import com.josh.usersrestapi.dto.EditUserDto;
import com.josh.usersrestapi.dto.LoginDto;
import com.josh.usersrestapi.dto.UserDto;
import com.josh.usersrestapi.model.BlackListedToken;
import com.josh.usersrestapi.model.User;
import com.josh.usersrestapi.utility.Response;

import java.util.List;

/**
 * @author Tejashree Surve
 * @Purpose : This is User Service Interface.
 */
public interface IUserService {

    User registerUser(String userEmail,UserDto userDto);

    List<User> getAllUser(String userEmail);

    User login(String userEmail,LoginDto loginDto);

    BlackListedToken logout(String token);

    User updateUser(String userEmail,EditUserDto editUserDto);

    User validateUser(String Email);

}
