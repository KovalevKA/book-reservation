package com.example.bookreservation.service;

import com.example.bookreservation.dto.ReservDTO;
import com.example.bookreservation.entity.Reserv;
import com.example.bookreservation.mapper.ReservMapper;
import com.example.bookreservation.repository.ReservRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservService extends AbstractServiceImpl<Reserv, ReservDTO, ReservRepository, ReservMapper>{
}
