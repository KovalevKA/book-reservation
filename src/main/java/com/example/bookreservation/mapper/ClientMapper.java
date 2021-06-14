package com.example.bookreservation.mapper;

import com.example.bookreservation.dto.ClientDTO;
import com.example.bookreservation.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper implements AbstractMapper<Client, ClientDTO> {

    @Override
    public ClientDTO toDTO(Client client) {
        return mapper.map(client, ClientDTO.class);
    }

    @Override
    public Client toEntity(ClientDTO clientDTO) {
        return mapper.map(clientDTO, Client.class);
    }

}
