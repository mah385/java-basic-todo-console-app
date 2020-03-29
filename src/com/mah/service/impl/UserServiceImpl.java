package com.mah.service.impl;

import com.mah.entity.User;
import com.mah.repository.impl.UserRepositoryImpl;
import com.mah.service.UserService;

public class UserServiceImpl implements UserService {

    private static UserService userService = null;

    private UserServiceImpl() {
    }

    public static UserService getUserServiceImpl() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    @Override
    public boolean registerUser(User user) {
        return UserRepositoryImpl.getUserRepositoryImpl().registerUser(user);
    }

    @Override
    public User loginUser(String loginEmailOrUserName, String loginPassword) {
        return UserRepositoryImpl.getUserRepositoryImpl().loginUser(loginEmailOrUserName, loginPassword);
    }

}
