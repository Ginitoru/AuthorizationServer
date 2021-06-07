package com.iordache.persistence.services;

import com.iordache.entity.Client;
import org.springframework.transaction.annotation.Transactional;

public interface ClientService {

    @Transactional
    void createClient(Client client);
}
