package com.mah.repository;

import com.mah.entity.User;

public interface UserRepository {

    boolean registerUser(User user);

    User loginUser(String loginEmailOrUserName, String loginPassword);

}
