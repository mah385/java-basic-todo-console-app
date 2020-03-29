package com.mah.controller;

import com.mah.entity.User;
import com.mah.service.impl.UserServiceImpl;

public class UserController {

    private static UserController userController = null;

    private UserController() {
    }

    public static UserController getUserController() {
        if (userController == null) {
            userController = new UserController();
        }
        return userController;
    }

    public boolean registerUser(User user) {
        return UserServiceImpl.getUserServiceImpl().registerUser(user);
    }

    public User loginUser(String loginEmailOrUserName, String loginPassword) {
        return UserServiceImpl.getUserServiceImpl().loginUser(loginEmailOrUserName, loginPassword);
    }

}
