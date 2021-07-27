package com.example.bookreservation.repository;

import com.example.bookreservation.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    //User findByLogin(String login);

}
