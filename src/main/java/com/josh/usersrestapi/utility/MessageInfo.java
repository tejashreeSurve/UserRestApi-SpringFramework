package com.josh.usersrestapi.utility;

import org.springframework.stereotype.Component;

@Component
public class MessageInfo {

    public String Bad_Request= "400";
    public String Success_Request = "200";

    public String User_Exist = "Please try with other Email-id";
    public String User_Register = "User Register Successfully";
    public String User_Display = "User is Successfully Displayed";
}
