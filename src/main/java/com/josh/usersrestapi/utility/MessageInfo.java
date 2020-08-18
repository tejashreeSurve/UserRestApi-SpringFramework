package com.josh.usersrestapi.utility;

/**
 * @author Tejashree Surve
 * @Purpose : This is Message Info class which contain message for user.
 */
public class MessageInfo {

    // Regex Constant
    public static final String Email_Regex = "\\w+\\@\\w+\\.\\w+";
    public static final String Password_Regex = "\\w+\\d+";
    // message
    public static final String Invalid_Email_Format = "Please Enter valid Email-id";
    public static final String Invalid_Password_Format = "Password must contain both character and numeric value";
    public static final String User_Exist = "Please Enter Valid Credentials Details";
    public static final String User_Not_Exist = "Please Enter Valid Credentials Details";
    public static final String User_Register = "User Register Successfully";
    public static final String User_Display = "User is Successfully Displayed";
    public static final String Invalid_Token = "Invalid token";
    public static final String User_Updated = "User is Successfully Updated";
    public static final String Verified_User = "User is Successfully Verified";
    public static final String Invalid_Password = "Invalid Password";
    public static final String User_Login = "User is Successfully Login";
    public static final String User_Not_Verified = "User is not Verified";
    public static final String User_Logout = "User is Successfully Log-out";
    public static final String User_Not_Save = "Sorry, your registration attempt was unsuccessful";
    public static final String UserList_Not_Exist = "User List Not Exist";
    public static final String Unauthorized_Access = "Unauthorized access";
    public static final String User_Not_Update = "Sorry, updation attempt was unsuccessful";
    public static final String User_Validation = "Sorry, Validation attempt was unsuccessful";
}
