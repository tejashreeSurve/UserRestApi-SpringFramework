package com.josh.usersrestapi.services;

import com.josh.usersrestapi.dto.EditUser;
import com.josh.usersrestapi.dto.LoginDto;
import com.josh.usersrestapi.dto.UserDto;
import com.josh.usersrestapi.utility.Response;

public interface IUserServices {

    Response registerUser(UserDto userDto);

    Response getAllUser();

    Response login(LoginDto loginDto);

    Response logout();

    Response updateUser(EditUser editUser);

    Response validateUser(String token);

}
