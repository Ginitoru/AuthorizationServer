package com.iordache.persistence.services.impl;

import com.iordache.persistence.repositories.ClientRepository;
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
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientRepository.findClientByClientId(clientId);

    }
}
