package com.iordache.web.controllers;

import com.iordache.entity.User;
import com.iordache.persistence.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/app")
public class UserController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @GetMapping("/user")
    public User createUser(@RequestBody User user){
        userService.createUser(user);
        return user;
    }

    @GetMapping("/user/{usernameOrEMailOrPhoneNumber}")
    public UserDetails getUser(@PathVariable String usernameOrEMailOrPhoneNumber){
        return userDetailsService.loadUserByUsername(usernameOrEMailOrPhoneNumber);

    }


}
