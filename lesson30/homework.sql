-- Задание 2
INSERT INTO "mySchema".books
    (book_id, title, author, published_year, genre)
VALUES (nextval('"mySchema".employees_book_id_seq'::regclass), 'harry potter', 'J. K. Rowling', 1997, 'fantasy ');

INSERT INTO "mySchema".books
    (book_id, title, author, published_year, genre)
VALUES (nextval('"mySchema".employees_book_id_seq'::regclass), 'The Talisman', 'Stephen King', 1984, 'fantasy ');

INSERT INTO "mySchema".books
    (book_id, title, author, published_year, genre)
VALUES (nextval('"mySchema".employees_book_id_seq'::regclass), 'Misery', 'Stephen King', 1987, 'fantasy ');

INSERT INTO "mySchema".books
    (book_id, title, author, published_year, genre)
VALUES (nextval('"mySchema".employees_book_id_seq'::regclass), 'The Lord of the Rings', 'J. R. R. Tolkien', 1954,
        'fantasy novel ');

INSERT INTO "mySchema".readers
    (reader_id, "name", email, phone)
VALUES (nextval('"mySchema".readers_reader_id_seq'::regclass), 'Mark', 'mark11@mail.ru', '89035556788');

INSERT INTO "mySchema".readers
    (reader_id, "name", email, phone)
VALUES (nextval('"mySchema".readers_reader_id_seq'::regclass), 'Steven', 'steven33@mail.ru', '89039956799');

INSERT INTO "mySchema".readers
    (reader_id, "name", email, phone)
VALUES (nextval('"mySchema".readers_reader_id_seq'::regclass), 'Peter', 'griffin@mail.ru', '55555');

INSERT INTO "mySchema".readers
    (reader_id, "name", email, phone)
VALUES (nextval('"mySchema".readers_reader_id_seq'::regclass), 'Helen', 'helen@mail.ru', '33333');

INSERT INTO "mySchema".borrowed_books
    (borrow_id, book_id, reader_id, borrow_date, return_date, status)
VALUES (nextval('"mySchema".borrowed_books_borrow_id_seq'::regclass), 1, 2, '2025-06-15', '2025-06-20', 'borrowed');

INSERT INTO "mySchema".borrowed_books
    (borrow_id, book_id, reader_id, borrow_date, return_date, status)
VALUES (nextval('"mySchema".borrowed_books_borrow_id_seq'::regclass), 4, 3, '2025-03-20', '2025-05-20', 'returned');

INSERT INTO "mySchema".borrowed_books
    (borrow_id, book_id, reader_id, borrow_date, return_date, status)
VALUES (nextval('"mySchema".borrowed_books_borrow_id_seq'::regclass), 3, 1, '2025-02-10', '2025-03-15', 'returned');

UPDATE "mySchema".books
SET genre='drama'
WHERE book_id = '3';

UPDATE "mySchema".borrowed_books
SET return_date='2025-06-21',
    status='returned'
WHERE borrow_id = '1';

DELETE
FROM "mySchema".readers
WHERE reader_id = '4';

DROP TABLE IF EXISTS borrowed_books;

CREATE TABLE borrowed_books
(
    borrow_id   SERIAL PRIMARY KEY,
    book_id     INT  NOT NULL,
    reader_id   INT  NOT NULL,
    borrow_date DATE NOT NULL,
    return_date DATE,
    status      VARCHAR(20) CHECK (status IN ('borrowed', 'returned')),
    FOREIGN KEY (book_id) REFERENCES books (book_id) ON DELETE CASCADE,
    FOREIGN KEY (reader_id) REFERENCES readers (reader_id) ON DELETE CASCADE
);

DELETE FROM "mySchema".borrowed_books
WHERE book_id ='4';

SELECT *
FROM "mySchema".books;

SELECT *
FROM "mySchema".readers;

SELECT *
FROM "mySchema".borrowed_books;

--Задание 3
-- Транзакция с ROLLBACK
begin;

INSERT INTO "mySchema".books
(book_id, title, author, published_year, genre)
VALUES (nextval('"mySchema".employees_book_id_seq'::regclass), 'War and Peace', 'Lev Tolstoy', 1867, 'fantasy ');

INSERT INTO "mySchema".borrowed_books
(book_id, reader_id, borrow_date, status)
VALUES (
           currval('"mySchema".employees_book_id_seq'::regclass),
           '2',
           CURRENT_DATE,
           'borrowed'
       );

SELECT * FROM "mySchema".books WHERE title = 'War and Peace';

ROLLBACK;

SELECT * FROM "mySchema".books WHERE title = 'War and Peace';

--Транзакция с COMMIT
begin;

INSERT INTO "mySchema".books
(book_id, title, author, published_year, genre)
VALUES (nextval('"mySchema".employees_book_id_seq'::regclass), 'War and Peace', 'Lev Tolstoy', 1867, 'fantasy ');

INSERT INTO "mySchema".borrowed_books
(book_id, reader_id, borrow_date, status)
VALUES (
           currval('"mySchema".employees_book_id_seq'::regclass),
           '2',
           CURRENT_DATE,
           'borrowed'
       );
COMMIT;

SELECT * FROM "mySchema".books WHERE title = 'War and Peace';

-- Задание 4
SELECT
    b.title AS "Название книги",
    b.author AS "Автор",
    bb.reader_id AS "ID читателя",
    bb.borrow_date AS "Дата выдачи"
FROM "mySchema".books b
         INNER JOIN "mySchema".borrowed_books bb ON b.book_id = bb.book_id
WHERE bb.status = 'borrowed'
ORDER BY bb.borrow_date DESC;

SELECT
    r.reader_id AS "ID читателя",
    r.name AS "Имя читателя",
    COUNT(bb.borrow_id) AS "Количество выданных книг"
FROM "mySchema".readers r
         LEFT JOIN
     "mySchema".borrowed_books bb ON r.reader_id = bb.reader_id
         AND bb.status = 'borrowed'
GROUP BY r.reader_id, r.name
ORDER BY "Количество выданных книг" DESC;

SELECT
    r.reader_id AS "ID читателя",
    r.name AS "Имя читателя",
    r.email AS "Email",
    COUNT(bb.borrow_id) AS "Количество выданных книг"
FROM "mySchema".readers r
         JOIN "mySchema".borrowed_books bb ON r.reader_id = bb.reader_id
WHERE bb.status = 'borrowed'
GROUP BY r.reader_id, r.name, r.email
HAVING COUNT(bb.borrow_id) > 2
ORDER BY "Количество выданных книг" DESC;

SELECT
    b.title AS "Название",
    b.author AS "Автор",
    bb.borrow_date AS "Когда взяли",
    r.name AS "Кто взял"
FROM books b
         LEFT JOIN  borrowed_books bb ON b.book_id = bb.book_id AND bb.status = 'borrowed'
         LEFT JOIN readers r ON bb.reader_id = r.reader_id
WHERE  b.genre = 'fantasy'
ORDER BY  b.title;
