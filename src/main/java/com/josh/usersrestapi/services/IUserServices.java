package com.josh.usersrestapi.services;

import com.josh.usersrestapi.dto.EditUserDto;
import com.josh.usersrestapi.dto.LoginDto;
import com.josh.usersrestapi.dto.UserDto;
import com.josh.usersrestapi.utility.Response;

/**
 * @author Tejashree Surve
 * @Purpose : This is User Service Interface.
 */

public interface IUserServices {

    Response registerUser(UserDto userDto);

    Response getAllUser(String token);

    Response login(LoginDto loginDto);

    Response logout(String token);

    Response updateUser(String token, EditUserDto editUserDto);

    Response validateUser(String token);

}
