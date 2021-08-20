package com.example.bookreservation.config;

import com.example.bookreservation.controller.AuthorController;
import com.example.bookreservation.controller.BookController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    private void init() {
        register(AuthorController.class);
        register(BookController.class);
    }
}
