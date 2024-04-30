package com.maidtask.trial.patrons;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.maidtask.trial.books.Book;


@Table("borrowing")
public class PatronBorrowings {

    @Column("id")
    @Id
    private Integer id;

    @Column("book_id")
    private final AggregateReference<Book, Integer> book;
    
    @Column("return_date")
    private LocalDate returnDate;

    @Column("borrowing_date")
    private LocalDate borrowDate;

    public PatronBorrowings(AggregateReference<Book, Integer> book) {
        this.book = book;
        this.borrowDate = LocalDate.now();
    }

    public void setReturnDate(LocalDate return_date) {
        this.returnDate = LocalDate.now();
    }

    public AggregateReference<Book, Integer> book() {
        return book;
    }

    public Optional<Integer> getId() {
        return Optional.of(book.getId());
    }
}
