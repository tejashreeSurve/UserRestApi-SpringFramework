package com.josh.usersrestapi.services;

import com.josh.usersrestapi.dto.EditUserDto;
import com.josh.usersrestapi.dto.LoginDto;
import com.josh.usersrestapi.dto.UserDto;
import com.josh.usersrestapi.model.UserEntity;
import com.josh.usersrestapi.repository.UserRepository;
import com.josh.usersrestapi.utility.JwtToken;
import com.josh.usersrestapi.utility.MessageInfo;
import com.josh.usersrestapi.utility.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tejashree Surve
 * @Purpose : This is Service class for User Operation.
 */
@Service
public class UserServiceImp implements IUserServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MessageInfo messageInfo;
    @Autowired
    private JwtToken jwtToken;

    // Register User
    @Override
    public Response registerUser(UserDto userDto) {
        String checkEmail = userDto.getEmail();
        UserEntity userIsPresent = userRepository.findByEmail(checkEmail);
        // check if user is present or not
        if (userIsPresent != null)
            return new Response(Integer.parseInt(messageInfo.Bad_Request), messageInfo.User_Exist, "Try with other Email-id");
        // generate token for validation
        String token = jwtToken.generateToken(checkEmail);
        System.out.println(token);
        // map with user dto with user entity
        UserEntity userData = modelMapper.map(userDto, UserEntity.class);
        // save data in database
        userRepository.save(userData);
        return new Response(Integer.parseInt(messageInfo.Success_Request), messageInfo.User_Register, "Successfully Registered");
    }

    // Get All User
    @Override
    public Response getAllUser() {
        return new Response(Integer.parseInt(messageInfo.Success_Request), messageInfo.User_Display, userRepository.findAll());
    }

    // Login User
    @Override
    public Response login(LoginDto loginDto) {
        UserEntity user = userRepository.findByEmail(loginDto.getUserEmail());
        String token = jwtToken.generateToken(loginDto.getUserEmail());
        System.out.println(token);
        // check if user is present
        if (user == null)
            return new Response(Integer.parseInt(messageInfo.Bad_Request), messageInfo.User_Not_Exist, "Invalid Email-id");
        // check if user is validate
        if (user.isValidate()) {
            // check if password is correct
            if (user.getPassword().equals(loginDto.getPassword())) {
                return new Response(Integer.parseInt(messageInfo.Success_Request), messageInfo.User_Login, "User is Successfully Login");
            } else {
                return new Response(Integer.parseInt(messageInfo.Bad_Request), messageInfo.Invalid_Password, "Please enter valid password");
            }
        }
        return new Response(Integer.parseInt(messageInfo.Bad_Request), token, "Please Verify User before Login");
    }

    // Logout User
    @Override
    public Response logout(String token) {
        jwtToken.refreshToken(token);
        return new Response(Integer.parseInt(messageInfo.Success_Request), messageInfo.User_Logout, "Successfully Log-out");
    }

    // Update User
    @Override
    public Response updateUser(String token, EditUserDto editUserDto) {
        String userEmail = jwtToken.getToken(token);
        UserEntity isUserPresent = userRepository.findByEmail(userEmail);
        // check if user is present
        if (isUserPresent == null)
            return new Response(Integer.parseInt(messageInfo.Bad_Request), messageInfo.User_Not_Exist, "Invalid token");
        // set all user data
        isUserPresent.setFirstName(editUserDto.getFirstName());
        isUserPresent.setLastName(editUserDto.getLastName());
        isUserPresent.setBirthdate(editUserDto.getBirthdate());
        userRepository.save(isUserPresent);
        return new Response(Integer.parseInt(messageInfo.Success_Request), messageInfo.User_Updated, isUserPresent);
    }

    // Validate User
    @Override
    public Response validateUser(String token) {
        String userEmail = jwtToken.getToken(token);
        UserEntity isUserPresent = userRepository.findByEmail(userEmail);
        // check if user is present
        if (isUserPresent == null)
            return new Response(Integer.parseInt(messageInfo.Bad_Request), messageInfo.User_Not_Exist, "Invalid token");
        // set validate value to true
        isUserPresent.setValidate(true);
        userRepository.save(isUserPresent);
        return new Response(Integer.parseInt(messageInfo.Success_Request), messageInfo.Verified_User, "You can Login Successfully");
    }
}
