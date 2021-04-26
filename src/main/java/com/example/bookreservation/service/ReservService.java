package com.example.bookreservation.service;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.dto.ReservDTO;
import com.example.bookreservation.entity.Reserv;
import com.example.bookreservation.mapper.ReservMapper;
import com.example.bookreservation.repository.ReservRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservService extends AbstractServiceImpl<Reserv, ReservDTO, ReservRepository, ReservMapper> {

    @Autowired
    private ReservRepository reservRepository;
    @Autowired
    private ReservMapper reservMapper;

    @Value("books.counts.forclient")
    Integer booksCountsForClient;


    public List<ReservDTO> getReservationClientListById(Long id){
        return reservMapper.toDTOs(reservRepository.getReservationClientListById(id));
    }

    public List<BookDTO> make(Long id, List<Long> bookIds){

        Integer count = reservRepository.getReservationClientListById(id).size();
        if ((count + bookIds.size()) > booksCountsForClient)
            throw new IllegalArgumentException("Too many books. U already have " + count + " books");

        

        return null;
    }


}
