package com.iordache.persistence.services.impl;

import com.iordache.entity.User;
import com.iordache.persistence.repositories.UserRepository;
import com.iordache.persistence.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    @Transactional
    public void createUser(User user){

       Optional<User> optUser = userRepository.findUserByEmail(user.getEmail());

       if(optUser.isEmpty()){
           userRepository.createUser(user);
           return;
       }

       throw  new RuntimeException("User already exists");

    }


    @Override
    @Transactional
    public User findUserByUserName(String username){
        return userRepository.findUserByUsername(username)
                             .orElseThrow(() -> new RuntimeException("User not found by username"));
    }


    @Override
    @Transactional
    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email)
                             .orElseThrow(() -> new RuntimeException("User not found by email"));
    }


    @Override
    @Transactional
    public User findUserByPhoneNumber(String phoneNumber){
        return userRepository.findUserByPhoneNumber(phoneNumber)
                             .orElseThrow(() -> new RuntimeException("User not found by phoneNumber"));
    }




}
