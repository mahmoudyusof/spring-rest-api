package com.maidtask.trial.books;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("books")
public record Book(
    @Id
    Integer id,
    @Column
    String title,
    @Column
    String author,
    @Column
    int publish_year,
    @Column
    String isbn,
    @Version
    Integer version
) {}
