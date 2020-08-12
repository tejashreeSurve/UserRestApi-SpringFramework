package com.josh.usersrestapi;

import com.josh.usersrestapi.dto.UserDto;
import com.josh.usersrestapi.services.UserServiceImp;
import com.josh.usersrestapi.utility.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;


public class UserControllerTest {

    @Autowired
    UserServiceImp userServiceImp;

}
