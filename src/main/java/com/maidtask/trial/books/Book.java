package com.maidtask.trial.books;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Table("books")
public class Book {


    @Id
    private final Integer id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String author;
    @NotEmpty
    @Min(1200)
    @Max(2024)
    private int publish_year;
    @NotEmpty
    private String isbn;
    @Version
    private final Integer version;


    public Book(Integer id, String title, String author, int publish_year, String isbn, Integer version) {
        this.title = title;
        this.author = author;
        this.publish_year = publish_year;
        this.isbn = isbn;

        this.id = id;
        this.version = version;
    }

    public Integer id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public int publish_year() {
        return publish_year;
    }

    public String isbn() {
        return isbn;
    }

    public Integer version() {
        return version;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublish_year(int publish_year) {
        this.publish_year = publish_year;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}
