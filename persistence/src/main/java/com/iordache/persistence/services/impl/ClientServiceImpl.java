package com.iordache.persistence.services.impl;

import com.iordache.entity.Client;
import com.iordache.persistence.repositories.ClientRepository;
import com.iordache.persistence.services.ClientService;
import com.iordache.securityClient.SecurityClient;
import lombok.AllArgsConstructor;


import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Primary
public class ClientServiceImpl implements ClientDetailsService, ClientService {


    private final ClientRepository clientRepository;

    @Override
    @Transactional
    public ClientDetails loadClientByClientId(String clientId){

        System.out.println(clientRepository.findClientByClientId(clientId));

       return clientRepository.findClientByClientId(clientId)
                                .map(client -> new SecurityClient(client))
                                .orElseThrow(() -> new ClientRegistrationException("Client not found"));

    }

    @Override
    @Transactional
    public void createClient(Client client){
        clientRepository.findClientByClientId(client.getClientId())
                        .ifPresentOrElse(theClient -> throwsException(),
                                        () -> clientRepository.save(client));


    }

    private void throwsException(){
        throw new RuntimeException();
    }

}
