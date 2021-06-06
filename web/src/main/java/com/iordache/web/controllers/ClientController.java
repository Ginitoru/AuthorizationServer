package com.iordache.web.controllers;

import com.iordache.entity.Client;
import com.iordache.persistence.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/server")
public class ClientController {

    private final ClientRepository clientRepository;


    @PostMapping("/client")
    public void createClient(@RequestBody Client client){

        clientRepository.save(client);

    }
}
