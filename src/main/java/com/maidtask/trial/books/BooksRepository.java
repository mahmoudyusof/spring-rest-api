package com.maidtask.trial.books;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class BooksRepository {
        private final JdbcClient jdbcClient;

        public BooksRepository(JdbcClient jdbcClient) {
            this.jdbcClient = jdbcClient;
        }

        public List<Book> getBooks() {
            return this.jdbcClient.sql("SELECT * FROM books")
            .query(Book.class)
            .list();
        }

        public Book getBookById(int id) {
            return this.jdbcClient.sql("SELECT * FROM books WHERE id = :id")
            .param("id", id)
            .query(Book.class).single();
        }

        public void addBook(Book book) {
            System.out.println("=================>>>>>>>>>>>>>>>>>>" + book);
            var updated = this.jdbcClient
            .sql("INSERT INTO books (title, author, publish_year, isbn) VALUES (?,?,?,?)")
            .params(List.of(book.title(), book.author(), book.publish_year(), book.isbn()))
            .update();

            Assert.state(updated == 1, "Couldn't Add Book " + book.title());
        }

        public void deleteBook(int id) {
            var deleted = this.jdbcClient
            .sql("DELETE FROM books WHERE id = :id")
            .param("id", id)
            .update();

            Assert.state(deleted == 1, "Couldn't Delete Book with id " + id);
        }
    }
