package com.josh.usersrestapi.services;

import com.josh.usersrestapi.dto.EditUserDto;
import com.josh.usersrestapi.dto.LoginDto;
import com.josh.usersrestapi.dto.UserDto;
import com.josh.usersrestapi.exception.UpdateUserException;
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
    private MessageInfo messageInfo;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private BlackListTokenRepository blackListTokenRepository;

    private static final Logger LOGGER = Logger.getLogger(UserServiceImp.class);
    /**
     * Add new User in Database.
     * @param userDto User Dto object.
     * @return saveUser User Entity object.
     */
    @Override
    public User registerUser(UserDto userDto) {
        // map with user dto with user entity
        User userData = modelMapper.map(userDto, User.class);
        // save data in database
        User saveUser =userRepository.save(userData);
        LOGGER.info("User is saved Successfully");
        return saveUser;
    }

    /**
     * Get all User from Database.
     * @return list of User type.
     */
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    /**
     * Login User by checking User credentials.
     * @param loginDto Login dto object.
     * @param user User Entity object.
     * @return user User Entity object.
     */
    @Override
    public User login(LoginDto loginDto,User user) {
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
     * @param userId User id for updating.
     * @param editUserDto EditUserDto object.
     * @return editedUser Updated User Entity object.
     */
    @Override
    public User updateUser(Integer userId,EditUserDto editUserDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UpdateUserException(messageInfo.User_Not_Exist));
        // set all user data
        user.setFirstName(editUserDto.getFirstName());
        user.setLastName(editUserDto.getLastName());
        user.setBirthdate(editUserDto.getBirthdate());
        User editedUser = userRepository.save(user);
        LOGGER.info("User is Updated Successfully");
        return editedUser;
    }

    /**
     * Validate user through token.
     * @param user User Entity object.
     * @return validateUser Updated User Entity object.
     */
    @Override
    public User validateUser(User user) {
        // set validate value to true
        user.setValidate(true);
        User validateUser = userRepository.save(user);
        LOGGER.info("User is Validated Successfully");
        return validateUser;
    }
}
