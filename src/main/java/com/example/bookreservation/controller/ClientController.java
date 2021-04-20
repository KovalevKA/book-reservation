package com.example.bookreservation.controller;

import com.example.bookreservation.dto.ClientDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clients")
public class ClientController {

    @GetMapping
    public ResponseEntity<ClientDTO> getClientByParams(@RequestParam String name){
        return ResponseEntity.ok(null);
    }
}
