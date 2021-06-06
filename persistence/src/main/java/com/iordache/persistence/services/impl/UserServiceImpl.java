package com.iordache.persistence.services.impl;

import com.iordache.entity.User;
import com.iordache.exceptions.errors.UserAlreadyExists;
import com.iordache.exceptions.errors.UserNotFoundException;
import com.iordache.persistence.repositories.UserRepository;
import com.iordache.persistence.services.UserService;
import com.iordache.persistence.utility.Verify;
import com.iordache.securityUser.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;



    @Override
    @Transactional  //method 1
    public void createUser(User user){

        userRepository.findUserByEmail(user.getEmail())
                     .ifPresentOrElse(theUser -> throwsException(),
                                     () -> create(user));

    }

                    //method 2
    private void create(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

                    //metod 3
    private void throwsException(){
        throw  new UserAlreadyExists("User already exists");
    }



    @Override
    @Transactional
    public User findUserByUserName(String username){
        return userRepository.findUserByUsername(username)
                             .orElseThrow(() -> new UserNotFoundException("User not found by username"));
    }


    @Override
    @Transactional
    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email)
                             .orElseThrow(() -> new UserNotFoundException("User not found by email"));
    }


    @Override
    @Transactional
    public User findUserByPhoneNumber(String phoneNumber){
        return userRepository.findUserByPhoneNumber(phoneNumber)
                             .orElseThrow(() -> new UserNotFoundException("User not found by phoneNumber"));
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEMailOrPhoneNumber) throws UsernameNotFoundException {



        if(Verify.isEmail(usernameOrEMailOrPhoneNumber)){
            User user = userRepository.findUserByEmail(usernameOrEMailOrPhoneNumber)
                                      .orElseThrow(() -> new UserNotFoundException("User not found by email"));

            return new SecurityUser(user);
        }


        if(Verify.isPhoneNumber(usernameOrEMailOrPhoneNumber)){
            User user = userRepository.findUserByPhoneNumber(usernameOrEMailOrPhoneNumber)
                                      .orElseThrow(() -> new UserNotFoundException("User not found by phoneNumber"));

            return new SecurityUser(user);
        }


        User user = userRepository.findUserByUsername(usernameOrEMailOrPhoneNumber)
                                  .orElseThrow(() -> new UserNotFoundException("User not found by username"));

        return new SecurityUser(user);

    }
}
