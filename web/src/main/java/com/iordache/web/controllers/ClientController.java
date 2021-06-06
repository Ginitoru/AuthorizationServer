package com.iordache.web.controllers;

import com.iordache.entity.Client;
import com.iordache.persistence.repositories.ClientRepository;
import com.iordache.persistence.services.ClientService;
import com.iordache.securityClient.SecurityClient;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/server")
public class ClientController {

    private final ClientService clientService;



    @GetMapping("/client")
    public void createClient(@RequestBody Client client){

        clientService.createClient(client);

    }

}
