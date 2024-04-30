package com.maidtask.trial.patrons.dto;

import java.util.List;

public record EagerPatronDTO(
    Integer id,
    String full_name,
    String email,
    List<BookBorrowDTO> books
) {}
