package com.example.bookreservation.service;

import com.example.bookreservation.dto.ReservDTO;
import com.example.bookreservation.entity.Book;
import com.example.bookreservation.entity.Client;
import com.example.bookreservation.entity.Reserv;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.mapper.ReservMapper;
import com.example.bookreservation.repository.BookRepository;
import com.example.bookreservation.repository.ClientRepository;
import com.example.bookreservation.repository.ReservRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private AbstractMapper<Reserv, ReservDTO> reservMapper;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ClientRepository clientRepository;

    public List<ReservDTO> getReservationClientListById(Long id) {
        return reservMapper.toDTOs(reservRepository.findReservsByClientIdAndAndReservationDateCancelGreaterThan(id, new Date()));
    }

    public List<ReservDTO> checkReservedBooksByBookId(List<Long> ids) {
        return reservMapper.toDTOs(reservRepository.findReservsByBookIdInAndReservationDateCancelGreaterThanEqual(ids, new Date()));
    }

    public List<ReservDTO> make(Long id, List<Long> bookIds, String dateTo) {
        if (bookIds.isEmpty())
            throw new IllegalArgumentException("No book to add");
        if (bookIds.size() > booksCountForClient)
            throw new IllegalArgumentException("Books too match");
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date.compareTo(new Date()) <= 0)
            throw new IllegalArgumentException("Date isn't correct");
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
        List<Book> books = bookRepository.getFreeBooksByListId(bookIds);
        List<Reserv> reservs = new ArrayList<>();
        Date finalDate = date;
        books.forEach(book -> reservs.add(new Reserv(client, book, finalDate)));
        reservs.forEach(reserv -> reservRepository.save(reserv));
        return reservMapper.toDTOs(reservs);
    }

    public Integer cancel(Long id, List<Long> listReservId) {
        List<Reserv> reservs = reservRepository.findReservsByIdInAndReservationDateCancelGreaterThanEqual(listReservId, new Date());
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
