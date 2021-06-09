package com.example.bookreservation.service;

import com.example.bookreservation.dto.ClientDTO;
import com.example.bookreservation.entity.Client;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ClientService extends
    AbstractServiceImpl<Client, ClientDTO, ClientRepository, AbstractMapper<Client, ClientDTO>> {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AbstractMapper<Client, ClientDTO> clientMapper;

    @Override
    public Mono<ClientDTO> editById(Long id, ClientDTO clientDTO) {
        return clientRepository.findByClientId(id)
            .map(client -> {
                client.setName(clientDTO.getName());
                return client;
            })
            .flatMap(clientRepository::save)
            .map(clientMapper::toDTO);
    }
}
