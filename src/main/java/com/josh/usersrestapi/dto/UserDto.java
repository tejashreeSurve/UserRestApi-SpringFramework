package com.josh.usersrestapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.josh.usersrestapi.utility.MessageInfo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * @author Tejashree Surve
 * @Purpose : This is DTO for User.
 */
public class UserDto {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private LocalDate birthdate;
    @NotEmpty
    @Pattern(regexp = MessageInfo.Email_Regex, message = MessageInfo.Invalid_Email_Format)
    private String email;
    @NotEmpty
    @Pattern(regexp = MessageInfo.Password_Regex, message = MessageInfo.Invalid_Password_Format)
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
