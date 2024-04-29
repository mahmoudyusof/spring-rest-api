package com.maidtask.trial.books;

import org.springframework.data.repository.ListCrudRepository;

public interface BooksRepository extends ListCrudRepository<Book, Integer>{}
