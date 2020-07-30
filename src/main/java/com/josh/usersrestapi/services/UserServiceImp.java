package com.josh.usersrestapi.services;

import com.josh.usersrestapi.dto.EditUser;
import com.josh.usersrestapi.dto.LoginDto;
import com.josh.usersrestapi.dto.UserDto;
import com.josh.usersrestapi.model.UserEntity;
import com.josh.usersrestapi.repository.UserRepository;
import com.josh.usersrestapi.utility.MessageInfo;
import com.josh.usersrestapi.utility.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements IUserServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MessageInfo messageInfo;

    @Override
    public Response registerUser(UserDto userDto) {
        UserEntity userIsPresent = userRepository.findByEmail(userDto.getEmail());
        if(userIsPresent != null)
            return new Response(Integer.parseInt(messageInfo.Bad_Request),messageInfo.User_Exist,"Try with other Email-id");
        UserEntity userData = modelMapper.map(userDto,UserEntity.class);
        userRepository.save(userData);
        return new Response(Integer.parseInt(messageInfo.Success_Request),messageInfo.User_Register,"Successfully Register");
    }

    @Override
    public Response getAllUser() {
        System.out.println(userRepository.findAll());
        return new Response(Integer.parseInt(messageInfo.Success_Request),messageInfo.User_Display,userRepository.findAll());
    }

    @Override
    public Response login(LoginDto loginDto) {
        return null;
    }

    @Override
    public Response logout() {
        return null;
    }

    @Override
    public Response updateUser(EditUser editUser) {
        return null;
    }

    @Override
    public Response validateUser(String token) {

        return null;
    }
}
