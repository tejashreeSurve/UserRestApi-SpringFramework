package com.josh.usersrestapi.dto;

import com.josh.usersrestapi.utility.MessageInfo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author Tejashree Surve
 * @Purpose : This is DTO for Login.
 */
public class LoginDto {
    @NotEmpty
    @Pattern(regexp = MessageInfo.Email_Regex, message = MessageInfo.Invalid_Email_Format)
    private String userEmail;
    @NotEmpty
    @Pattern(regexp = MessageInfo.Password_Regex, message = MessageInfo.Invalid_Password_Format)
    private String password;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
