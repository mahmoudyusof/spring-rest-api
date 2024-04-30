package com.maidtask.trial.books;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    private final BooksRepository booksRespository;

    public BooksController(BooksRepository booksRespository) {
        this.booksRespository = booksRespository;
    }
    
    @GetMapping("")
    public List<Book> getBooks() {
        return booksRespository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Integer id) {
        Book book = booksRespository.findById(id).orElse(null);
        if(book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        return book;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void addBook(@Valid @RequestBody Book book) {
        booksRespository.save(book);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void updateBook(@PathVariable Integer id) {
        booksRespository.deleteById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void deleteBook(@PathVariable Integer id, @RequestBody Book bookUpdate) {
        Book book = booksRespository.findById(id).orElse(null);
        if(book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }

        book.setAuthor(bookUpdate.author());
        book.setIsbn(bookUpdate.isbn());
        book.setPublish_year(bookUpdate.publish_year());
        book.setTitle(bookUpdate.title());

        booksRespository.save(book);
    }
}
