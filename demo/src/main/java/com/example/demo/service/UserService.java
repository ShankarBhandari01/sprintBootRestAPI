package com.example.demo.service;

import com.example.demo.domains.AppUser;
import com.example.demo.domains.Roles;

import java.util.List;


public interface UserService {
    AppUser saveUser(AppUser user);

    Roles saveRole(Roles roles);

    void addRoleToUser(String username, String rolename);

    AppUser getUser(String username);

    List<AppUser> getUsers();
}
