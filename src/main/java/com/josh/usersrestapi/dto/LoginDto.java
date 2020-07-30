package com.josh.usersrestapi.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author Tejashree Surve
 * @Purpose : This is DTO for Login.
 */
public class LoginDto {
    @NotEmpty
    @Pattern(regexp = "\\w+\\@\\w+\\.\\w+", message = "Please Enter Email-id")
    private String userEmail;
    @NotEmpty
    @Pattern(regexp = "\\w+\\d+", message = "Password must contain both character and numeric value")
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
