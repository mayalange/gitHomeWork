CREATE TABLE books
(
    book_id        SERIAL PRIMARY KEY,
    title          VARCHAR(255) NOT NULL,
    author         VARCHAR(255) NOT null,
    published_year INT CHECK (published_year > 0),
    genre          VARCHAR(100)
);

CREATE TABLE readers
(
    reader_id SERIAL PRIMARY key,
    name      VARCHAR(100) NOT null,
    email     VARCHAR(255) UNIQUE NOT null,
    phone     VARCHAR(15) UNIQUE
);

CREATE TABLE borrowed_books
(
    borrow_id   SERIAL PRIMARY key,
    book_id     INT  NOT null,
    reader_id   INT  NOT null,
    borrow_date DATE NOT null,
    return_date DATE,
    status      VARCHAR(20) CHECK (status IN ('borrowed', 'returned')),
    FOREIGN KEY (book_id) REFERENCES books (book_id),
    FOREIGN KEY (reader_id) REFERENCES readers (reader_id)
);

CREATE UNIQUE INDEX idx_unique_title ON books(title);

CREATE UNIQUE INDEX idx_unique_readers ON borrowed_books(reader_id) WHERE status = 'borrowed';