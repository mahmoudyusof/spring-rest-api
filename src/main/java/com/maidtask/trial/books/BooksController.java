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

@RestController
@RequestMapping("/api/books")
public class BooksController {

    private final BooksRepository booksRespository;

    public BooksController(BooksRepository booksRespository) {
        this.booksRespository = booksRespository;
    }
    
    @GetMapping("")
    public List<Book> getBooks() {
        return booksRespository.getBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Integer id) {
        return booksRespository.getBookById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void addBook(@RequestBody Book book) {
        booksRespository.addBook(book);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void updateBook(@PathVariable Integer id) {
        booksRespository.deleteBook(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void deleteBook(@PathVariable Integer id, @RequestBody Book book) {
        booksRespository.deleteBook(id);
        booksRespository.addBook(book);
    }
}
