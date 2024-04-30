package com.maidtask.trial.patrons;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.maidtask.trial.books.BooksRepository;
import com.maidtask.trial.patrons.dto.BookBorrowDTO;
import com.maidtask.trial.patrons.dto.EagerPatronDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PatronController {

    private final PatronRepository patronRepository;
    private final BooksRepository bookRepository;
    private final Logger logger = LoggerFactory.getLogger(PatronController.class);

    public PatronController(PatronRepository patronRepository, BooksRepository bookRepository) {
        this.patronRepository = patronRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/patrons")
    public List<EagerPatronDTO> getPatrons() {
        List<Patron> patrons = patronRepository.findAll();
        return patrons.stream().map(patron -> {
            List<BookBorrowDTO> books = bookRepository.findByPatronId(patron.id());

            return new EagerPatronDTO(patron.id(), patron.fullName(), patron.email(), books);

        }).collect(Collectors.toList());
    }

    @GetMapping("/patrons/{id}")
    public EagerPatronDTO getPatron(@PathVariable Integer id) {
        Patron patron = patronRepository.findById(id).orElse(null);

        if(patron == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patron not found");
        }

        List<BookBorrowDTO> books = bookRepository.findByPatronId(patron.id());

        return new EagerPatronDTO(patron.id(), patron.fullName(), patron.email(), books);
    }

    @PostMapping("/patrons")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPatron(@Valid @RequestBody Patron patron) {
        patronRepository.save(patron);
    }

    @DeleteMapping("/patrons/{id}")
    public void deletePatron(@PathVariable Integer id) {
        patronRepository.deleteById(id);
    }

    @PutMapping("/patrons/{id}")
    public void updatePatron(@PathVariable Integer id, @RequestBody Patron patronUpdate) {
        Patron patron = patronRepository.findById(id).orElse(null);
        if (patron == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patron not found");
        }

        patron.setFullName(patronUpdate.fullName());
        patron.setEmail(patronUpdate.email());

        patronRepository.save(patron);
    }

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public void borrowBook(@PathVariable Integer bookId, @PathVariable Integer patronId) {
        Patron patron = patronRepository.findById(patronId).orElse(null);
        if (patron == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patron not found");
        }
        try {
            patron.borrowBook(bookId);
            patronRepository.save(patron);
            logger.info("Borrowed book with id: " + bookId + " for patron with id: " + patronId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public void returnBook(@PathVariable Integer bookId, @PathVariable Integer patronId) {
        Patron patron = patronRepository.findById(patronId).orElse(null);
        ;
        if (patron == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patron not found");
        }
        try {
            patron.returnBook(bookId);
            patronRepository.save(patron);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book wasn't borrowed or not found");
        }
    }
}
