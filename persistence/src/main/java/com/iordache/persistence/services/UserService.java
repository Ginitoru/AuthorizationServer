package com.iordache.persistence.services;

import com.iordache.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    void createUser(User user);

    @Transactional
    User findUserByUserName(String username);

    @Transactional
    User findUserByEmail(String email);

    @Transactional
    User findUserByPhoneNumber(String phoneNumber);
}
