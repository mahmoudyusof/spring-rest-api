package com.maidtask.trial;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.maidtask.trial.books.BooksRepository;
import com.maidtask.trial.books.Book;
import com.maidtask.trial.patrons.Patron;
import com.maidtask.trial.patrons.PatronRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	CommandLineRunner run(PatronRepository patronRepository, BooksRepository booksRepository) {
		return args -> {
			// create 5 patrons
			patronRepository.save(new Patron(null, "John Doe", "john@doe.com", new ArrayList<>(), null));
			patronRepository.save(new Patron(null, "Jane Doe", "jane@doe.com", new ArrayList<>(), null));
			patronRepository.save(new Patron(null, "Alice", "alice@google.com", new ArrayList<>(), null));
			patronRepository.save(new Patron(null, "Bob", "bob@something.com", new ArrayList<>(), null));
			patronRepository.save(new Patron(null, "Charlie", "charlie@hotmail.com", new ArrayList<>(), null));

			// create 10 books
			booksRepository.save(new Book(null, "The Great Gatsby", "F. Scott Fitzgerald", 1999, "99586665841", null));
			booksRepository.save(new Book(null, "To Kill a Mockingbird", "Harper Lee", 2001, "99586665842", null));
			booksRepository.save(new Book(null, "1984", "George Orwell", 2003, "99586665843", null));
			booksRepository.save(new Book(null, "Pride and Prejudice", "Jane Austen", 2005, "99586665844", null));
			booksRepository.save(new Book(null, "The Catcher in the Rye", "J.D. Salinger", 2007, "99586665845", null));
			booksRepository.save(new Book(null, "The Lord of the Rings", "J.R.R. Tolkien", 2009, "99586665846", null));
			booksRepository.save(new Book(null, "Animal Farm", "George Orwell", 2011, "99586665847", null));
			booksRepository.save(new Book(null, "Brave New World", "Aldous Huxley", 2013, "99586665848", null));
			booksRepository.save(new Book(null, "The Hobbit", "J.R.R. Tolkien", 2015, "99586665849", null));
			booksRepository.save(new Book(null, "Fahrenheit 451", "Ray Bradbury", 2017, "99586665850", null));
			

		};
	}

}
