package com.josh.usersrestapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josh.usersrestapi.controller.UserController;
import com.josh.usersrestapi.dto.EditUserDto;
import com.josh.usersrestapi.dto.LoginDto;
import com.josh.usersrestapi.dto.UserDto;
import com.josh.usersrestapi.model.User;
import com.josh.usersrestapi.services.IUserService;
import com.josh.usersrestapi.utility.Response;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UsersRestApiApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private IUserService userServices;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getAll()throws Exception{
        User user = new User();
        user.setId(1);
        user.setFirstName("teju");
        List<User> allUser = Arrays.asList(user);
        Response response = new Response(200,"User is Successfully Displayed",allUser);
        given(userServices.getAllUser()).willReturn(allUser);
        mockMvc.perform(get("/user/getAllUser/eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJ0ZWp1MTIzQGdtYWlsLmNvbSIsImlhdCI6MTU5NjEyMzIzNX0.ANBDh01HjrZCcQp3OA9ZvI563IkGbhlG0sg2LYoHzrk").content(MediaType.APPLICATION_JSON_VALUE)).
                andExpect(status().isOk()).andExpect(jsonPath("$.statuscode",is(response.getStatuscode())))
                .andExpect(jsonPath("$.message",is(response.getMessage())))
                .andExpect(jsonPath("$.data[0].id",is(user.getId())));
    }

    @Test
    public void addUser() throws Exception{
        User user= new User();
        user.setId(1);
        user.setFirstName("kanak");
        user.setLastName("Surve");
        user.setBirthdate(LocalDate.parse("2008-11-10"));
        user.setEmail("kanak@gmail.com");
        user.setPassword("kanak");
        Response response = new Response(200,"User Register Successfully",user);
        String userDtoString = "{\"firstName\":\"kanak\",\"lastName\":\"Surve\", \"birthdate\":\"2008-11-10\", \"email\":\"kanak@gmail.com\", \"password\":\"kanak\" }";
        when(userServices.registerUser(ArgumentMatchers.any(UserDto.class))).thenReturn(user);
        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(userDtoString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statuscode",is(response.getStatuscode())))
                .andExpect(jsonPath("$.message",is(response.getMessage())))
                .andExpect(jsonPath("$.data.firstName",is(user.getFirstName())))
                .andDo(print());
    }

    @Test
    public void loginUser() throws  Exception{
        LoginDto loginDto = new LoginDto();
        loginDto.setUserEmail("tanvi123@gmail.com");
        loginDto.setPassword("teju123");
        Response response = new Response(200,"User is Successfully Login","User is Successfully Login");
        when(userServices.login(ArgumentMatchers.any(LoginDto.class))).thenReturn(response);
        mockMvc.perform(post("/user/login").content(new ObjectMapper().writeValueAsString(loginDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statuscode",is(response.getStatuscode())))
                .andExpect(jsonPath("$.message",is(response.getMessage())))
                .andExpect(jsonPath("$.data",is(response.getData())))
                .andDo(print());
    }

    @Test
    public void editUser() throws Exception{
        User user = new User();
        user.setId(2);
        user.setFirstName("teju");
        user.setLastName("surve");
        user.setBirthdate(LocalDate.parse("2008-11-10"));
        user.setEmail("teju123@gmail.com");
        user.setPassword("teju123");
        Response response = new Response(200,"User is Successfully Updated",user);
        String userDtoString = "{\"firstName\":\"teju\",\"lastName\":\"surve\", \"birthdate\":\"2008-11-10\" }";
        when(userServices.updateUser(ArgumentMatchers.anyInt(),ArgumentMatchers.any(EditUserDto.class))).thenReturn(user);
        mockMvc.perform(post("/user/updateUser/"+"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJ0ZWp1MTIzQGdtYWlsLmNvbSIsImlhdCI6MTU5NjEyMzIzNX0.ANBDh01HjrZCcQp3OA9ZvI563IkGbhlG0sg2LYoHzrk")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(userDtoString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statuscode",is(response.getStatuscode())))
                .andExpect(jsonPath("$.message",is(response.getMessage())))
                .andExpect(jsonPath("$.data.firstName",is(user.getFirstName())))
                .andDo(print());
    }
}
