package com.example.bookreservation.service;

import static java.time.LocalDate.now;

import com.example.bookreservation.dto.ReservDTO;
import com.example.bookreservation.entity.Reserv;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.BookRepository;
import com.example.bookreservation.repository.ClientRepository;
import com.example.bookreservation.repository.ReservRepository;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReservService {

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

    public Flux<ReservDTO> getReservationClientListById(Long id) {
        return reservRepository.findByClientIdAndAndReservationDateCancelGreaterThan(id, new Date())
            .map(reserv -> new ReservDTO(reservMapper.toDTO(reserv)));
    }

    public Flux<ReservDTO> checkReservedBooksByBookId(List<Long> ids) {
        return reservRepository
            .findByBookIdInAndReservationDateCancelGreaterThanEqual(ids, new Date())
            .map(reserv -> new ReservDTO(reservMapper.toDTO(reserv)));
    }

    public Flux<ReservDTO> make(Long id, List<Long> bookIds, String dateTo)
        throws IllegalArgumentException {
        /*if (bookIds.isEmpty()) {
            throw new IllegalArgumentException("No book to add");
        }
        if (bookIds.size() > booksCountForClient) {
            throw new IllegalArgumentException("Books too match");
        }
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date.compareTo(new Date()) <= 0) {
            throw new IllegalArgumentException("Date isn't correct");
        }
        Client client = clientRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        List<Book> books = bookRepository.getFreeBooksByListId(bookIds);
        List<Reserv> reservs = new ArrayList<>();
        Date finalDate = date;
        books.forEach(book -> reservs.add(new Reserv(client, book, finalDate)));
        reservs.forEach(reserv -> reservRepository.save(reserv));
        return reservMapper.toDTOs(reservs);*/

        return bookRepository.getFreeBooksByListId(bookIds)
            .map(book -> new Reserv(id, book.getBookId(), now()))
            .map(reserv -> {
                reservRepository.save(reserv);
                return reservMapper.toDTO(reserv);
            });
    }

    public Mono<Long> cancel(Long id, List<Long> listReservId) throws IllegalArgumentException {
        AtomicInteger counter = new AtomicInteger();
        return reservRepository
            .findByIdInAndReservationDateCancelGreaterThanEqual(id, listReservId, new Date())
            .map(reserv -> {
                reserv.setReservationDateCancel(now());
                reservRepository.save(reserv);
                return reserv;
            }).count();
    }
}
