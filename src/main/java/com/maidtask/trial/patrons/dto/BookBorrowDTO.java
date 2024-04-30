package com.maidtask.trial.patrons.dto;

public record BookBorrowDTO(
    Integer id,
    String title,
    String author,
    String borrowing_date,
    String return_date
) {}
