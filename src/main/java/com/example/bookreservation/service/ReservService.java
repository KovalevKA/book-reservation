package com.example.bookreservation.service;

import com.example.bookreservation.dto.ReservDTO;
import com.example.bookreservation.entity.Book;
import com.example.bookreservation.entity.Client;
import com.example.bookreservation.entity.Reserv;
import com.example.bookreservation.mapper.ReservMapper;
import com.example.bookreservation.repository.BookRepository;
import com.example.bookreservation.repository.ClientRepository;
import com.example.bookreservation.repository.ReservRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ReservService
        extends AbstractServiceImpl<Reserv, ReservDTO, ReservRepository, ReservMapper> {

    @Value("${books.forClient.count}")
    private Integer booksCountForClient;

    @Autowired
    private ReservRepository reservRepository;
    @Autowired
    private ReservMapper reservMapper;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ClientRepository clientRepository;

    public List<ReservDTO> getReservationClientListById(Long id) {
        return reservMapper.toDTOs(reservRepository.getReservationClientListById(id));
    }

    public List<ReservDTO> checkReservedBooksByBookId(List<Long> ids) {
        return reservMapper.toDTOs(reservRepository.getReservsByBookIds(ids));
    }

    public List<ReservDTO> make(Long id, List<Long> bookIds, Date dateTo) {

        Integer count = reservRepository.getReservationClientListById(id).size();
        if ((count + bookIds.size()) > booksCountForClient)
            throw new IllegalArgumentException("Too many books. U already have " + count + " books");
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
        List<Book> books = bookRepository.getFreeBooksByListId(bookIds);
        List<Reserv> reservs = new ArrayList<>();
        books.forEach(book -> reservs.add(new Reserv(client, book, dateTo)));
        reservs.forEach(reserv -> reservRepository.save(reserv));
        return reservMapper.toDTOs(reservs);
    }

    public Integer cancel(Long id, List<Long> listReservId) {
        List<Reserv> reservs = reservRepository.getReservByIds(listReservId);
        if (reservs.isEmpty()) throw new IllegalArgumentException("No reservations found");
        AtomicInteger count = new AtomicInteger();
        reservs.forEach(reserv -> {
            reserv.setReservationDateCancel(new Date());
            reservRepository.save(reserv);
            count.getAndIncrement();
        });
        return count.get();
    }
}
