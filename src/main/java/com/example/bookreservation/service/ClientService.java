package com.example.bookreservation.service;

import com.example.bookreservation.dto.ClientDTO;
import com.example.bookreservation.entity.Client;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends
        CommonServiceImpl<Client, ClientDTO, ClientRepository, AbstractMapper<Client, ClientDTO>> {

    public ClientService() {
        super();
        setClazz(Client.class);
    }
}
