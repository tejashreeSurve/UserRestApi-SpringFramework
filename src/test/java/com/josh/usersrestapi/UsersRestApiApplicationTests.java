package com.josh.usersrestapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josh.usersrestapi.dto.EditUserDto;
import com.josh.usersrestapi.dto.LoginDto;
import com.josh.usersrestapi.dto.UserDto;
import com.josh.usersrestapi.model.User;
import com.josh.usersrestapi.services.IUserService;
import com.josh.usersrestapi.utility.JwtTokenUtil;
import com.josh.usersrestapi.utility.Response;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UsersRestApiApplicationTests {
    @Autowired
    private  MockMvc mockMvc;
    @MockBean
    private IUserService userServices;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void getAll()throws Exception{
        User user = new User();
        user.setId(1);
        user.setFirstName("teju");
        List<User> allUser = new ArrayList<>();
        allUser.add(user);
        Response response = new Response(200,"User is Successfully Displayed",allUser);
        when(userServices.getAllUser()).thenReturn(allUser);
        System.out.println(when(userServices.getAllUser()).thenReturn(allUser));
        mockMvc.perform(get("/getAllUser").content(MediaType.APPLICATION_JSON_VALUE).header("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhbGthMTIzQGdtYWlsLmNvbSIsImlhdCI6MTU5NzI0NzE2MSwiZXhwIjoxNTk3MzMzNTYxfQ.-e2pmAer3GLPg16FeElnihY-qglYxOh0MOfheKogjq8"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.statuscode",is(response.getStatuscode())))
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
        user.setPassword("kanak123");
        Response response = new Response(200,"User Register Successfully",user);
        String userDtoString = "{\"firstName\":\"kanak\",\"lastName\":\"Surve\", \"birthdate\":\"2008-11-10\", \"email\":\"kanak@gmail.com\", \"password\":\"kanak123\" }";

        when(userServices.registerUser(ArgumentMatchers.any(UserDto.class))).thenReturn(user);

        mockMvc.perform(post("/registerUser")
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
        User user = new User ();
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
        String token = jwtTokenUtil.generateToken(user.getEmail());
        Response response = new Response(200,"User is Successfully Login",token);
        when(userServices.login(ArgumentMatchers.any(LoginDto.class),ArgumentMatchers.any(User.class))).thenReturn(user);
        mockMvc.perform(post("/login").content(new ObjectMapper().writeValueAsString(loginDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statuscode",is(response.getStatuscode())))
                .andExpect(jsonPath("$.message",is(response.getMessage())))
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
        mockMvc.perform(post("/updateUser")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(userDtoString)
                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhbGthMTIzQGdtYWlsLmNvbSIsImlhdCI6MTU5NzI0NzE2MSwiZXhwIjoxNTk3MzMzNTYxfQ.-e2pmAer3GLPg16FeElnihY-qglYxOh0MOfheKogjq8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statuscode",is(response.getStatuscode())))
                .andExpect(jsonPath("$.message",is(response.getMessage())))
                .andExpect(jsonPath("$.data.firstName",is(user.getFirstName())))
                .andDo(print());
    }

    @Test
    public void validateUser() throws  Exception{
        String token ="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJtYXl1QGdtYWlsLmNvbSIsImlhdCI6MTU5NzMwNzEzOCwiZXhwIjoxNTk3MzkzNTM4fQ.SXcFUbXrw4ZzS2qGsdxb5G_AQGQrpJwIXRC787T36ls";

        String userEmail =jwtTokenUtil.getAllClaims(token);
        User user= new User();
        user.setId(1);
        user.setFirstName("kanak");
        user.setLastName("Surve");
        user.setBirthdate(LocalDate.parse("2008-11-10"));
        user.setEmail("kanak@gmail.com");
        user.setPassword("kanak123");
        Response response = new Response(200,"User is Successfully Verified","You can Login Successfully");
        when(userServices.validateUser(ArgumentMatchers.any(User.class))).thenReturn(user);
        mockMvc.perform(get("/validateUser").content(MediaType.APPLICATION_JSON_VALUE).header("token",token))
                .andExpect(status().isOk()).andExpect(jsonPath("$.statuscode",is(response.getStatuscode())))
                .andExpect(jsonPath("$.message",is(response.getMessage())))
                .andExpect(jsonPath("$.data",is(response.getData())));
    }
}
