package com.maidtask.trial.patrons;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.maidtask.trial.patrons.dto.EagerPatronDTO;

public interface PatronRepository extends ListCrudRepository<Patron, Integer>{
     
    @Query("SELECT * FROM patrons JOIN borrowing ON patrons.id = borrowing.patron_id WHERE borrowing.user_id = :bookId")
    List<Patron> findByBookId(@Param("bookId") Integer bookId);


    @Query("SELECT p.id, p.full_name, p.email, bk.title, bk.author, b.borrowing_date, b.return_date FROM patrons p JOIN borrowing b ON p.id = b.patron_id JOIN books bk ON bk.id = b.book_id")
    List<EagerPatronDTO> findAllEager();

    @Query("SELECT p.id, p.full_name, p.email, bk.title, bk.author, b.borrowing_date, b.return_date FROM patrons p FULL JOIN borrowing b ON p.id = b.patron_id FULL JOIN books bk ON bk.id = b.book_id WHERE p.id = :id")
    List<EagerPatronDTO> findByIdEager(@Param("id") Integer id);
}
