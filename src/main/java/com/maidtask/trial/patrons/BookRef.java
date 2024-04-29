package com.maidtask.trial.patrons;

import java.time.LocalDate;

import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

@Table("borrowing")
public record BookRef(
    Integer book,
    LocalDate borrowing_date,
    LocalDate return_date,
    @Version
    Integer version
) {
    
}
