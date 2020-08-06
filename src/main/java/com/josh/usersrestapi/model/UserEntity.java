package com.josh.usersrestapi.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Tejashree Surve
 * @Purpose : This is Entity class for User.
 */
@Entity
@Component
@Table(name = "springuserdata")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String email;
    private String password;
    private boolean isValidate = false;
    private boolean isUserLogedin = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidate() {
        return isValidate;
    }

    public void setValidate(boolean validate) {
        isValidate = validate;
    }

    public boolean isUserLogedin() {
        return isUserLogedin;
    }

    public void setUserLogedin(boolean userLogedin) {
        isUserLogedin = userLogedin;
    }

}
