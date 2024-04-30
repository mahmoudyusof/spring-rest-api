package com.maidtask.trial.patrons;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;



@Table("patrons")
public class Patron {

    @Id
    private final Integer id;

    @Column("full_name")
    @NotEmpty
    private String fullName;
    
    @NotEmpty
    @Email
    private String email;

    @MappedCollection(idColumn = "patron_id", keyColumn = "id")
    private final List<PatronBorrowings> books;
    
    @Version
    private final Integer version;


    public Patron(Integer id, String fullName, String email, List<PatronBorrowings> books, Integer version) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.books = books;
        this.version = version;
    }


    public Integer id() {
        return id;
    }

    public String fullName() {
        return fullName;
    }

    public String email() {
        return email;
    }

    public Integer version() {
        return version;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public void borrowBook(Integer bookId) {
        this.books.add(new PatronBorrowings(AggregateReference.to(bookId)));
    }

    public void returnBook(Integer bookId) {
        PatronBorrowings book = this.books.stream()
        .filter(borrowing -> borrowing.getId().orElse(-1).equals(bookId))
        .findFirst().orElse(null);

        if(book == null) throw new IllegalArgumentException("Book not found");

        book.setReturnDate(LocalDate.now());
    }

    public Stream<Integer> getBorrowedBooksIds() {
        return this.books.stream()
        .map(PatronBorrowings::book)
        .map(AggregateReference::getId);
    }
}
