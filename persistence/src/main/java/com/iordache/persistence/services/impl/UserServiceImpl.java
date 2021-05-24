package com.iordache.persistence.services.impl;

import com.iordache.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl{

    private final UserRepository userRepository;



}
