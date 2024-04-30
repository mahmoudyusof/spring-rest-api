package com.maidtask.trial.books;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.maidtask.trial.patrons.dto.BookBorrowDTO;

public interface BooksRepository extends ListCrudRepository<Book, Integer>{

    @Query("SELECT bk.id, bk.title, bk.author, br.borrowing_date, br.return_date FROM books bk JOIN borrowing br ON bk.id = br.book_id WHERE br.patron_id = :patronId")
    List<BookBorrowDTO> findByPatronId(@Param("patronId") Integer patronId);
}
