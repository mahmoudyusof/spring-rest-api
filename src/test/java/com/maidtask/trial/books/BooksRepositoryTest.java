package com.maidtask.trial.books;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class BooksRepositoryTest {

    @Autowired
    BooksRepository booksRepository;

    private static final Integer ID_TO_DELETE = 3;

    @Test
    void testFindAll() {
        List<Book> books = booksRepository.findAll();
        assertEquals(10, books.size());
    }

    @Test
    void testFindById() {
        Book book = booksRepository.findById(1).orElse(null);
        assertNotNull(book);
        assertEquals("The Great Gatsby", book.title());
    }

    @Test
    void testDelete() {
        booksRepository.deleteById(ID_TO_DELETE);
        Book book = booksRepository.findById(ID_TO_DELETE).orElse(null);
        assertNull(book);
    }

    @Test
    void testUpdate() {
        Book book = booksRepository.findById(1).orElse(null);
        assertNotNull(book);
        assertNotEquals(book.title(), "The Great Gatsby 2");
        book.setTitle("The Great Gatsby 2");
        booksRepository.save(book);
        Book updatedBook = booksRepository.findById(1).orElse(null);
        assertNotNull(updatedBook);
        assertEquals("The Great Gatsby 2", updatedBook.title());
    }

}
