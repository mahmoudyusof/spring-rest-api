package com.maidtask.trial.patrons;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.maidtask.trial.books.Book;


@Table("patrons")
public record Patron(
    @Id
    Integer id,

    @Column("full_name")
    String fullName,
    
    String email,

    List<BookRef> books,
    
    @Version
    Integer version
) {

    public void borrowBook(Book book) {
        books.add(new BookRef(book.id(), LocalDate.now(), null, version));
    }

    public void returnBook(Book book) {
        // TODO: change borrwoing to mutable (class instead of record) and implement me
    }
}
