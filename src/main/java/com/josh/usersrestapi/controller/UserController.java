package com.josh.usersrestapi.controller;

import com.josh.usersrestapi.dto.EditUserDto;
import com.josh.usersrestapi.dto.LoginDto;
import com.josh.usersrestapi.dto.UserDto;
import com.josh.usersrestapi.exception.*;
import com.josh.usersrestapi.model.BlackListedToken;
import com.josh.usersrestapi.model.User;
import com.josh.usersrestapi.repository.BlackListTokenRepository;
import com.josh.usersrestapi.repository.UserRepository;
import com.josh.usersrestapi.services.IUserService;
import com.josh.usersrestapi.services.UserServiceImp;
import com.josh.usersrestapi.utility.JwtTokenUtil;
import com.josh.usersrestapi.utility.MessageInfo;
import com.josh.usersrestapi.utility.Response;
import com.sun.istack.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Tejashree Surve
 * @Purpose : This is RestApi Controller for User Operation.
 */
@RestController
public class UserController {
    @Autowired
    private IUserService userServices;
    @Autowired
    private MessageInfo messageInfo;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlackListTokenRepository blackListTokenRepository;

    private static final Logger LOGGER = Logger.getLogger(UserController.class);
    /**
     * New User Registration.
     * @param userDto User Dto object.
     * @return ResponseEntity object.
     */
    @PostMapping("/registerUser")
    public ResponseEntity<Response> register(@Valid @RequestBody UserDto userDto){
        String checkEmail = userDto.getEmail();
        User userIsPresent = userRepository.findByEmail(checkEmail);
        // check if user is present or not
        if (userIsPresent != null)
            throw new RegisterException(messageInfo.User_Exist);
        LOGGER.info("User is Successfully Validate for registerUser");
        // generate token for validation
        String token = jwtTokenUtil.generateToken(checkEmail);
        LOGGER.info("Token is Generated Successfully");
        System.out.println(token);
        User user = userServices.registerUser(userDto);
        if(user == null)
            throw new UserNotSaveException(messageInfo.User_Not_Save);
        return new ResponseEntity<Response>(new Response(Integer.parseInt(messageInfo.Success_Request), messageInfo.User_Register, user), HttpStatus.OK);
    }

    /**
     * Get All Register User.
     * @param token Jwt token for Authentication of Api's.
     * @return ResponseEntity object.
     */
    @GetMapping("/getAllUser")
    public ResponseEntity<Response> getAllUser(@RequestHeader String token){
        String userEmail = jwtTokenUtil.getAllClaims(token);
        User user = userRepository.findByEmail(userEmail);
        LOGGER.warning("Verify token from blackListToken ");
        blackListTokenRepository.findByToken(token).ifPresent(e->{throw new UnauthorizedAccessException(messageInfo.Unauthorized_Access);});
        LOGGER.info("Token is successfully Verified");
        if (user == null)
            throw new LoginException(messageInfo.User_Not_Exist);
        LOGGER.info("User is Successfully founds");
        if(user.isValidate()) {
            LOGGER.info("User is Successfully Validate for getAllUser");
            List<User> userList = userServices.getAllUser();
            if (userList == null)
                throw new ListNotExistException(messageInfo.UserList_Not_Exist);
            return new ResponseEntity<Response>(new Response(Integer.parseInt(messageInfo.Success_Request),messageInfo.User_Display,userList),HttpStatus.OK);
        }
        return new ResponseEntity<Response>(new Response(Integer.parseInt(messageInfo.Bad_Request),messageInfo.User_Not_Verified,"Validate User"),HttpStatus.BAD_REQUEST);
    }

    /**
     * User Login.
     * @param loginDto Login Dto object.
     * @return ResponseEntity object.
     */
    @PostMapping("/login")
    public ResponseEntity<Response> login(@Valid @RequestBody LoginDto loginDto){
        User user = userRepository.findByEmail(loginDto.getUserEmail());
        // check if user is present
        String token = jwtTokenUtil.generateToken(loginDto.getUserEmail());
        if (user == null)
            throw new LoginException(messageInfo.User_Not_Exist);
        LOGGER.info("User is Successfully Validate for login");
        User loginUser =  userServices.login(loginDto,user);
        if(loginUser != null) {
            if (loginUser.isValidate()) {
                return new ResponseEntity<Response>(new Response(Integer.parseInt(messageInfo.Success_Request), messageInfo.User_Login, token), HttpStatus.OK);
            }else{
                return new ResponseEntity<Response>(new Response(Integer.parseInt(messageInfo.Bad_Request), "Please Verify User before Login","Validate User"), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<Response>(new Response(Integer.parseInt(messageInfo.Bad_Request), messageInfo.Invalid_Password, "Please enter valid password"), HttpStatus.BAD_REQUEST);
    }

    /**
     * User Updation.
     * @param token Jwt token for Authentication of Rest Api's.
     * @param editUserDto EditUser  Dto object.
     * @return ResponseEntity object.
     */
    @PostMapping("/updateUser")
    public ResponseEntity<Response> update(@RequestHeader String token, @Valid @RequestBody EditUserDto editUserDto){
        String userEmail = jwtTokenUtil.getAllClaims(token);
        User isUserPresent = userRepository.findByEmail(userEmail);
        LOGGER.warning("Verify token from blackListToken ");
        blackListTokenRepository.findByToken(token).ifPresent(e->{throw new UnauthorizedAccessException(messageInfo.Unauthorized_Access);});
        LOGGER.info("Token is successfully Verified");
        // check if user is present
        if (isUserPresent == null)
            throw  new UpdateUserException(messageInfo.User_Not_Exist);
        LOGGER.info("User is Successfully Validate for updateUser");
        User editedUser= userServices.updateUser(isUserPresent.getId(),editUserDto);
        return new ResponseEntity<Response>(new Response(Integer.parseInt(messageInfo.Success_Request), messageInfo.User_Updated, editedUser), HttpStatus.OK);
    }

    /**
     * Validate User.
     * @param token Jwt token for Authentication for Rest Api's.
     * @return ResponseEntity object.
     */
    @GetMapping("/validateUser")
    public ResponseEntity<Response> validate(@RequestHeader String token){
        String userEmail = jwtTokenUtil.getAllClaims(token);
        User isUserPresent = userRepository.findByEmail(userEmail);
        LOGGER.warning("Verify token from blackListToken ");
        blackListTokenRepository.findByToken(token).ifPresent(e->{throw new UnauthorizedAccessException(messageInfo.Unauthorized_Access);});
        LOGGER.info("Token is successfully Verified");
        // check if user is present
        if (isUserPresent == null)
            throw new ValidateException(messageInfo.User_Not_Exist);
        LOGGER.info("User is Successfully Validate for validate");
        User user = userServices.validateUser(isUserPresent);
        return new ResponseEntity<Response>(new Response(Integer.parseInt(messageInfo.Success_Request), messageInfo.Verified_User, "You can Login Successfully"), HttpStatus.OK);
    }

    /**
     * User Logout.
     * @param token Jwt token for Authentication for Rest Api's.
     * @return ResponseEntity object.
     */
    @GetMapping("/logout")
    public ResponseEntity<Response> logout(@RequestHeader String token){
        BlackListedToken blackListedToken = userServices.logout(token);
        return new ResponseEntity<Response>(new Response(Integer.parseInt(messageInfo.Success_Request), messageInfo.User_Logout, "Successfully Log-out"), HttpStatus.OK);
    }
}
