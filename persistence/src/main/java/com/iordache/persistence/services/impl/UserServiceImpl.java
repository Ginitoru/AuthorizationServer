package com.iordache.persistence.services.impl;

import com.iordache.entity.User;
import com.iordache.persistence.repositories.UserRepository;
import com.iordache.persistence.services.UserService;
import com.iordache.securityUser.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void createUser(User user){

       Optional<User> optUser = userRepository.findUserByEmail(user.getEmail());

       if(optUser.isEmpty()){

           user.setPassword(
                            passwordEncoder.encode(user.getPassword())
           );

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


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEMailOrPhoneNumber) throws UsernameNotFoundException {



        if(usernameOrEMailOrPhoneNumber.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")){
            User user = userRepository.findUserByEmail(usernameOrEMailOrPhoneNumber)
                                      .orElseThrow(() -> new UsernameNotFoundException("User not found by email"));

            return new SecurityUser(user);
        }


        if(usernameOrEMailOrPhoneNumber.matches("^[0-9]$")){
            User user = userRepository.findUserByPhoneNumber(usernameOrEMailOrPhoneNumber)
                                      .orElseThrow(() -> new UsernameNotFoundException("User not found by phoneNumber"));

            return new SecurityUser(user);
        }


        User user = userRepository.findUserByUsername(usernameOrEMailOrPhoneNumber)
                                  .orElseThrow(() -> new UsernameNotFoundException("User not found by username"));

        return new SecurityUser(user);

    }
}
