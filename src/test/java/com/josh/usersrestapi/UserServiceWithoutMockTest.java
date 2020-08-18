package com.josh.usersrestapi;

import com.josh.usersrestapi.dto.EditUserDto;
import com.josh.usersrestapi.dto.LoginDto;
import com.josh.usersrestapi.dto.UserDto;
import com.josh.usersrestapi.model.User;
import com.josh.usersrestapi.services.IUserService;
import com.josh.usersrestapi.utility.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceWithoutMockTest {

    private MockMvc mockMvc;
    @Autowired
    IUserService userServices;

    @Test
    public void getAll(){
        User user = new User();
        user.setId(1);
        user.setFirstName("teju");
        List<User> allUser = new ArrayList<>();
        allUser.add(user);
        Response response = new Response(HttpStatus.BAD_REQUEST.value(),"User is Successfully Displayed",allUser);
        List<User> userList = userServices.getAllUser("Teju123@gmail.com");
        assertThat(userList).size().isGreaterThan(0);
    }

    @Test
    public  void addUser(){
        UserDto userDto = new UserDto();
        userDto.setFirstName("kanak");
        userDto.setLastName("Surve");
        userDto.setBirthdate(LocalDate.parse("2008-11-10"));
        userDto.setEmail("kanak@gmail.com");
        userDto.setPassword("kanak123");
        User user= new User();
        user.setId(1);
        user.setFirstName("kanak");
        user.setLastName("Surve");
        user.setBirthdate(LocalDate.parse("2008-11-10"));
        user.setEmail("kanak@gmail.com");
        user.setPassword("kanak123");
        User saveUser = userServices.registerUser("kanak@gmail.com",userDto);
        assertThat(saveUser.getFirstName()).isEqualTo(userDto.getFirstName());
    }

    @Test
    public void updateUser(){
        EditUserDto editUserDto = new EditUserDto();
        editUserDto.setFirstName("kanak");
        editUserDto.setLastName("yadav");
        editUserDto.setBirthdate(LocalDate.parse("2008-11-10"));
        User editeduser = userServices.updateUser("kanak@gmail.com",editUserDto);
        assertThat(editeduser.getFirstName()).isEqualTo(editeduser.getFirstName());
    }

    @Test
    public void login(){
        User user = new User();
        user.setId(18);
        user.setFirstName("alkaa");
        user.setLastName("yadav");
        user.setBirthdate(LocalDate.parse("1998-01-21"));
        user.setEmail("alka123@gmail.com");
        user.setPassword("alka123");
        user.setValidate(true);
        LoginDto loginDto = new LoginDto();
        loginDto.setUserEmail("alka123@gmail.com");
        loginDto.setPassword("alka123");
        User logedUser = userServices.login("kanak@gmail.com",loginDto);
        assertThat(loginDto.getPassword()).isEqualTo(logedUser.getPassword());
    }

    @Test
    public void validateUser(){
        User user = new User();
        user.setId(1);
        user.setFirstName("alkaa");
        user.setLastName("yadav");
        user.setBirthdate(LocalDate.parse("1998-01-21"));
        user.setEmail("alka123@gmail.com");
        user.setPassword("alka123");
        User validUser = userServices.validateUser("kanak@gmail.com");
        assertThat(validUser.isValidate()== true);
    }
}
