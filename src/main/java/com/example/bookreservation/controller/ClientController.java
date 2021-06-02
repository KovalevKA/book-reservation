package com.example.bookreservation.controller;

import com.example.bookreservation.dto.ClientDTO;
import com.example.bookreservation.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ClientDTO getClientByParams(@RequestParam String name) {
        return null;
    }

    @PostMapping
    public Mono<ClientDTO> addClient(@RequestBody ClientDTO clientDTO) {
        return clientService.create(clientDTO);
    }

    @PutMapping("{id}")
    public Mono<ClientDTO> editClient(@PathVariable Long id,
        @RequestBody ClientDTO clientDTO) {
        return clientService.editById(id, clientDTO);
    }

    @DeleteMapping("{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteById(id);
    }
}
