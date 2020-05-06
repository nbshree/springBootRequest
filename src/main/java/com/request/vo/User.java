package com.request.vo;

import lombok.Data;

import java.util.Set;

@Data
public class User {
    private String id;
    private String userName;
    private String password;
    private Set<Role> roles;

    public User(String id, String userName, String password, Set<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roles = roles;

    }
}
