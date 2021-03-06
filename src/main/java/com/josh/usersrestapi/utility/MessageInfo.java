package com.josh.usersrestapi.utility;

import org.springframework.stereotype.Component;
/**
 * @author Tejashree Surve
 * @Purpose : This is Message Info class which contain message for user.
 */
@Component
public class MessageInfo {
    // status code
    public String Bad_Request= "400";
    public String Success_Request = "200";
    // message
    public String User_Exist = "Please try with other Email-id";
    public String User_Not_Exist = "User not Exist with this Email-id";
    public String User_Register = "User Register Successfully";
    public String User_Display = "User is Successfully Displayed";
    public String Invalid_Token = "Invalid token";
    public String User_Updated = "User is Successfully Updated";
    public String Verified_User = "User is Successfully Verified";
    public String Invalid_Password = "Invalid Password";
    public String User_Login = "User is Successfully Login";
    public String User_Not_Verify = "User is not Verify";
    public String User_Logout = "User is Successfully Log-out";
}
