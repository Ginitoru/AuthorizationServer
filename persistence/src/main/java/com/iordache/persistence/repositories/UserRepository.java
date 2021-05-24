package com.iordache.persistence.repositories;

import com.iordache.entity.User;

import java.util.Optional;

public interface UserRepository {
    void createUser(User user);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByPhoneNumber(String phoneNumber);
}
