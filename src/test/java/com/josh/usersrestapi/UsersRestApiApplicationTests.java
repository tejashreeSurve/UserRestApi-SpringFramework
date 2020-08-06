package com.josh.usersrestapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josh.usersrestapi.controller.UserController;
import com.josh.usersrestapi.dto.EditUserDto;
import com.josh.usersrestapi.dto.LoginDto;
import com.josh.usersrestapi.dto.UserDto;
import com.josh.usersrestapi.model.UserEntity;
import com.josh.usersrestapi.repository.UserRepository;
import com.josh.usersrestapi.services.IUserServices;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
    private IUserServices userServices;
   // @Autowired
     //UserRepository userRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getAll()throws Exception{
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setFirstName("teju");
        List<UserEntity> allUser = Arrays.asList(userEntity);
        Response response = new Response(200,"User is Successfully Displayed",allUser);
        given(userServices.getAllUser()).willReturn(
                response
        );
        mockMvc.perform(get("/getAllUser").content(MediaType.APPLICATION_JSON_VALUE)).
                andExpect(status().isOk()).andExpect(jsonPath("$.statuscode",is(response.getStatuscode())))
                .andExpect(jsonPath("$.message",is(response.getMessage())))
                .andExpect(jsonPath("$.data[0].id",is(userEntity.getId())));
    }

    @Test
    public void addUser() throws Exception{
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setFirstName("kanak");
        userEntity.setLastName("Surve");
        userEntity.setBirthdate(LocalDate.parse("2008-11-10"));
        userEntity.setEmail("kanak@gmail.com");
        userEntity.setPassword("kanak");
        Response response = new Response(200,"User Register Successfully",userEntity);
        String userDtoString = "{\"firstName\":\"kanak\",\"lastName\":\"Surve\", \"birthdate\":\"2008-11-10\", \"email\":\"kanak@gmail.com\", \"password\":\"kanak\" }";
        when(userServices.registerUser(ArgumentMatchers.any(UserDto.class))).thenReturn(response);
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(userDtoString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statuscode",is(response.getStatuscode())))
                .andExpect(jsonPath("$.message",is(response.getMessage())))
                .andExpect(jsonPath("$.data.firstName",is(userEntity.getFirstName())))
                .andDo(print());
    }
    @Test
    public void loginUser() throws  Exception{
        LoginDto loginDto = new LoginDto();
        loginDto.setUserEmail("tanvi123@gmail.com");
        loginDto.setPassword("teju123");
        Response response = new Response(200,"User is Successfully Login","User is Successfully Login");
        when(userServices.login(ArgumentMatchers.any(LoginDto.class))).thenReturn(response);
       // UserEntity userEntity = userRepository.findByEmail(loginDto.getUserEmail());
       // System.out.println("User"+userEntity.isValidate());

    mockMvc.perform(post("/login").content(new ObjectMapper().writeValueAsString(loginDto))
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
        EditUserDto editUserDto = new EditUserDto();
       editUserDto.setFirstName("teju");
       editUserDto.setLastName("surve");
       editUserDto.setBirthdate(LocalDate.parse("2008-11-10"));
        UserEntity userEntity = new UserEntity();
        userEntity.setId(2);
        userEntity.setFirstName("teju");
        userEntity.setLastName("surve");
        userEntity.setBirthdate(LocalDate.parse("2008-11-10"));
        userEntity.setEmail("teju123@gmail.com");
        userEntity.setPassword("teju123");
        //System.out.println(new ObjectMapper().writeValueAsString(editUserDto));
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJ0ZWp1MTIzQGdtYWlsLmNvbSIsImlhdCI6MTU5NjEyMzIzNX0.ANBDh01HjrZCcQp3OA9ZvI563IkGbhlG0sg2LYoHzrk";
        Response response = new Response(200,"User is Successfully Updated",userEntity);
        String userDtoString = "{\"firstName\":\"teju\",\"lastName\":\"surve\", \"birthdate\":\"2008-11-10\" }";
        when(userServices.updateUser(token,editUserDto)).thenReturn(response);
       mockMvc.perform(post("/updateUser/"+"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJ0ZWp1MTIzQGdtYWlsLmNvbSIsImlhdCI6MTU5NjEyMzIzNX0.ANBDh01HjrZCcQp3OA9ZvI563IkGbhlG0sg2LYoHzrk")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(userDtoString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.statuscode",is(response.getStatuscode())))
//                .andExpect(jsonPath("$.message",is(response.getMessage())))
//                .andExpect(jsonPath("$.data.firstName",is(userEntity.getFirstName())))
                .andDo(print());
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
    }
}
