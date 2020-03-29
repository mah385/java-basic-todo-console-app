package com.mah.service;

import com.mah.entity.User;

public interface UserService {

    boolean registerUser(User user);

    User loginUser(String loginEmailOrUserName, String loginPassword);

}
