package com.example.bookreservation.mapper;

import com.example.bookreservation.dto.ClientDTO;
import com.example.bookreservation.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper implements AbstractMapper<Client, ClientDTO> {

  @Override
  public ClientDTO toDTO(Client client) {
    ClientDTO clientDTO = mapper.map(client, ClientDTO.class);
    return clientDTO;
  }

  @Override
  public Client toEntity(ClientDTO clientDTO) {
    Client client = mapper.map(clientDTO, Client.class);
    return client;
  }

}
