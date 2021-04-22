package com.example.bookreservation.service;

import com.example.bookreservation.dto.ClientDTO;
import com.example.bookreservation.entity.Client;
import com.example.bookreservation.mapper.ClientMapper;
import com.example.bookreservation.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends AbstractServiceImpl<Client, ClientDTO, ClientRepository, ClientMapper> {
}
