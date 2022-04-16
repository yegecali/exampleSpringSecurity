package com.yegecali.security.service;

import com.yegecali.security.model.Role;
import com.yegecali.security.model.UserAuth;

import java.util.List;

public interface UserService {
    UserAuth saveUser(UserAuth user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String rolename);
    UserAuth getUser(String username);
    List<UserAuth> getAll();
}
