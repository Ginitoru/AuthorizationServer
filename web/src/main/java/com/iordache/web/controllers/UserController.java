package com.iordache.web.controllers;

import com.iordache.entity.Client;
import com.iordache.entity.User;
import com.iordache.persistence.repositories.ClientRepository;
import com.iordache.persistence.services.UserService;
import com.iordache.persistence.services.impl.ClientServiceImpl;
import com.iordache.securityClient.SecurityClient;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/app")
public class UserController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final ClientRepository clientRepository;

    @GetMapping("/user")
    public User createUser(@RequestBody User user){
        userService.createUser(user);
        return user;
    }

    @GetMapping("/user/{usernameOrEMailOrPhoneNumber}")
    public UserDetails getUser(@PathVariable String usernameOrEMailOrPhoneNumber){
        return userDetailsService.loadUserByUsername(usernameOrEMailOrPhoneNumber);

    }

    @GetMapping("/client/{client}")
    public void show(@PathVariable String client){
        clientRepository.findClientByClientId(client)
                        .ifPresent(c-> System.out.println(new SecurityClient(c).getRegisteredRedirectUri().toString()));


    }




}
