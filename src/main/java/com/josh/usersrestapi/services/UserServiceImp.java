package com.josh.usersrestapi.services;

import com.josh.usersrestapi.dto.EditUserDto;
import com.josh.usersrestapi.dto.LoginDto;
import com.josh.usersrestapi.dto.UserDto;
import com.josh.usersrestapi.exception.*;
import com.josh.usersrestapi.model.BlackListedToken;
import com.josh.usersrestapi.model.User;
import com.josh.usersrestapi.repository.BlackListTokenRepository;
import com.josh.usersrestapi.repository.UserRepository;
import com.josh.usersrestapi.utility.JwtTokenUtil;
import com.josh.usersrestapi.utility.MessageInfo;
import com.sun.istack.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Tejashree Surve
 * @Purpose : This is Service class for User Operation.
 */
@Service
public class UserServiceImp implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private BlackListTokenRepository blackListTokenRepository;

    private static final Logger LOGGER = Logger.getLogger(UserServiceImp.class);
    /**
     * Add new User in Database.
     * @param userEmail User email for verification.
     * @param userDto User Dto object.
     * @return saveUser User Entity object.
     */
    @Override
    public User registerUser(String userEmail,UserDto userDto) {
        // check if email-id is present or not
        userRepository.findByEmail(userEmail).ifPresent(e->{throw new UserAlreadyExist(MessageInfo.User_Exist);});
        // map with user dto with user entity
        User userData = modelMapper.map(userDto, User.class);
        // save data in database
        User saveUser =userRepository.save(userData);
        LOGGER.info("User is saved Successfully");
        return saveUser;
    }

    /**
     * Get all User from Database.
     * @param userEmail User email for verification.
     * @return list of User type.
     */
    @Override
    public List<User> getAllUser(String userEmail) {
        // check if user exist or not
        Optional<User> optionalUserData = userRepository.findByEmail(userEmail);
        // throw exception if user not exist
        optionalUserData.orElseThrow(() ->new UserDoesNotExist(MessageInfo.User_Not_Exist));
        // get optionalUser into User type
        User user = optionalUserData.get();
        if(user.isValidate()) {
            LOGGER.info("Token is successfully Verified");
            return userRepository.findAll();
        }
        else
            throw new ValidateException(MessageInfo.User_Not_Verified);
    }

    /**
     * Login User by checking User credentials.
     * @param userEmail User userEmail for verification.
     * @param loginDto Login dto object.
     * @return user User Entity object.
     */
    @Override
    public User login(String userEmail,LoginDto loginDto) {
        // check if user exist or not
        Optional<User> optionalUserData = userRepository.findByEmail(userEmail);
        // throw exception if user not exist
        optionalUserData.orElseThrow(() ->new UserDoesNotExist(MessageInfo.User_Not_Exist));
        // get optionalUser into User type
        User user = optionalUserData.get();
        // check if password is correct
        if (user.getPassword().equals(loginDto.getPassword())) {
            LOGGER.info("User password is Successfully verified");
            return user;
        }
        return null;
    }

    /**
     * Logout User by entering token in BlackListedJwtToken Entity.
     * @param token User generate token.
     * @return editedTokenList BlackListedToken object.
     */
    @Override
    public BlackListedToken logout(String token) {
        BlackListedToken blackListedToken = new BlackListedToken();
        blackListedToken.setToken(token);
        LOGGER.info("Token is add into BlackListedTokenTable");
        BlackListedToken editedTokenList = blackListTokenRepository.save(blackListedToken);
        return editedTokenList;
    }

    /**
     * Update User by User id.
     * @param userEmail User email for updating.
     * @param editUserDto EditUserDto object.
     * @return editedUser Updated User Entity object.
     */
    @Override
    public User updateUser(String userEmail,EditUserDto editUserDto) {
        // check if user exist or not
        Optional<User> optionalUserData = userRepository.findByEmail(userEmail);
        // throw exception if user not exist
        optionalUserData.orElseThrow(() ->new UserDoesNotExist(MessageInfo.User_Not_Exist));
        // get optionalUser into User type
        User user = optionalUserData.get();
        if(user.isValidate()) {
            LOGGER.info("Token is successfully Verified");
            // set all user data
            user.setFirstName(editUserDto.getFirstName());
            user.setLastName(editUserDto.getLastName());
            user.setBirthdate(editUserDto.getBirthdate());
            User editedUser = userRepository.save(user);
            LOGGER.info("User is Updated Successfully");
            return editedUser;
        }
        else
            throw new ValidateException(MessageInfo.User_Not_Verified);
    }

    /**
     * Validate user through token.
     * @param userEmail User userEmail for verification.
     * @return validateUser Updated User Entity object.
     */
    @Override
    public User validateUser(String userEmail) {
        // check if user exist or not
        Optional<User> optionalUserData = userRepository.findByEmail(userEmail);
        // throw exception if user not exist
        optionalUserData.orElseThrow(() ->new UserDoesNotExist(MessageInfo.User_Not_Exist));
        LOGGER.info("User is Successfully Validate for validate");
        // get optionalUser into User type
        User user = optionalUserData.get();
        // set validate value to true
        user.setValidate(true);
        User validateUser = userRepository.save(user);
        LOGGER.info("User is Validated Successfully");
        return validateUser;
    }
}
