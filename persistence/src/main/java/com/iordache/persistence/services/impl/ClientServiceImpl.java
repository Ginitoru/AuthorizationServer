package com.iordache.persistence.services.impl;

import com.iordache.persistence.repositories.ClientRepository;
import com.iordache.securityClient.SecurityClient;
import lombok.AllArgsConstructor;


import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientDetailsService {


    private final ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId){

        System.out.println(clientRepository.findClientByClientId(clientId));

       return clientRepository.findClientByClientId(clientId)
                                .map(client -> new SecurityClient(client))
                                .orElseThrow(() -> new ClientRegistrationException("Client not found"));

    }
}
