package com.josh.usersrestapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @NotNull(message = "First Name must not be null")
    private String firstName;
    @NotEmpty
    @NotNull(message = "Last Name must not be null")
    private String lastName;
    @NotNull(message = "Birth Date must not be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    @NotEmpty
    @NotNull(message = "Email-id must not be null")
    @Pattern(regexp = "\\w+\\@\\w+\\.\\w+", message = "Please Enter Email-id")
    private String email;
    @NotEmpty
    @NotNull(message = "Password must not be null")
    @Pattern(regexp = "\\w+\\d+", message = "Password must contain both character and numeric value")
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

    public UserDto(@NotEmpty String firstName, @NotEmpty String lastName, @NotNull LocalDate birthdate, @NotEmpty @Pattern(regexp = "\\w+\\@\\w+\\.\\w+", message = "Please Enter Email-id") String email, @NotEmpty @Pattern(regexp = "\\w+\\d+", message = "Password must contain both character and numeric value") String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
    }

    public UserDto(){

    }
}
