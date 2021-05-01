package com.example.bookreservation.controller;

import com.example.bookreservation.dto.ClientDTO;
import com.example.bookreservation.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<ClientDTO> getClientByParams(@RequestParam String name) {
        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> addClient(@RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.create(clientDTO));
    }

    @PatchMapping("{id}")
    public ResponseEntity<ClientDTO> editClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.editById(id, clientDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
