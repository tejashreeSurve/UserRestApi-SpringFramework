package com.josh.usersrestapi.model;

import javax.persistence.*;
/**
 * @author Tejashree Surve
 * @Purpose : This is Entity class for BlackListJwtToke Table.
 */
@Entity
@Table(name = "blacklistjwttoken")
public class BlackListedToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String token;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
