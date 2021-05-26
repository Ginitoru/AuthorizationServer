package com.iordache.web.controllers;

import com.iordache.entity.User;
import com.iordache.persistence.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @GetMapping("/user")
    public User createUser(@RequestBody User user){
        userService.createUser(user);
        return user;
    }

    @GetMapping("/security/{var}")
    public UserDetails getUser(@PathVariable String var){
        return userDetailsService.loadUserByUsername(var);

    }


}
