DROP TABLE IF EXISTS borrowing;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS patrons;

CREATE TABLE IF NOT EXISTS books (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  author VARCHAR(255) NOT NULL,
  publish_year INT NOT NULL,
  isbn VARCHAR(255) NOT NULL,
  version INT
);


CREATE TABLE IF NOT EXISTS patrons (
  id SERIAL PRIMARY KEY,
  full_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  version INT
);

CREATE TABLE IF NOT EXISTS borrowing (
  book_id INT NOT NULL,
  patron_id INT NOT NULL,
  borrowing_date DATE NOT NULL,
  return_date DATE,
  PRIMARY KEY (book_id, patron_id),
  version INT
);