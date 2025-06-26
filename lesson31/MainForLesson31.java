import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainForLesson31 implements LibraryApi {
    private static String url;
    private static String user;
    private static String password;

    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
        Class.forName("org.postgresql.Driver");
        url = "jdbc:postgresql://localhost:5432/mydatabase";
        user = "admin";
        password = "admin";

        // Проверка подключения
        try (Connection testConn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to database successfully!");
        }
        MainForLesson31 app = new MainForLesson31();
        Book book = new Book();
        book.setTitle("Wuthering Heights");
        book.setAuthor("emily bronte");
        book.setPublishedYear(1847);
        book.setGenre("drama");

        Reader reader = new Reader();
        reader.setName("mike");
        reader.setEmail("66@mail.ru");
        reader.setPhone("66666");

        Reader readerToUpdate = new Reader();
        readerToUpdate.setReaderId(1);
        readerToUpdate.setName("леша");
        readerToUpdate.setEmail("new@email.com");
        readerToUpdate.setPhone("3333");

        app.addBook(book);
        app.updateBookStatus(5,"returned");
        app.addReader(reader);
        app.getAllOccupiedBooks();
        app.updateReader(readerToUpdate);
        app.filterBooksByStatus("returned");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = sdf.parse("2025-06-10");
        java.sql.Date searchDate = new java.sql.Date(utilDate.getTime());
        app.findBooksBorrowedAfterDate(searchDate);

    }

    @Override
    public Book addBook(Book book) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO \"mySchema\".books (title, author, published_year, genre) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getPublishedYear());
            statement.setString(4, book.getGenre());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            System.out.println("id = " + generatedKeys.getInt("book_id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
        return book;
    }

    @Override
    public Book updateBookStatus(int bookId, String status) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        try (PreparedStatement statement = connection.prepareStatement("UPDATE \"mySchema\".borrowed_books SET status = ?, return_date = ? WHERE book_id = ? AND status = 'borrowed'")) {
            statement.setString(1, status);
            statement.setDate(2, new Date(System.currentTimeMillis()));
            statement.setInt(3, bookId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
        Book book = new Book();
        book.setBookId(bookId);
        return book;
    }

    @Override
    public Reader addReader(Reader reader) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO \"mySchema\".readers (name, email, phone) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, reader.getName());
            statement.setString(2, reader.getEmail());
            statement.setString(3, reader.getPhone());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            System.out.println("id = " + generatedKeys.getInt("reader_id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
        return reader;
    }

    @Override
    public List<OccupiedBook> getAllOccupiedBooks() throws SQLException {
        List<OccupiedBook> occupiedBooks = new ArrayList<>();
        Connection connection = DriverManager.getConnection(url, user, password);

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT b.book_id, b.title, b.author, b.published_year, b.genre, " +
                        "       r.reader_id, r.name, r.email, r.phone, " +
                        "       bb.borrow_date " +
                        "FROM \"mySchema\".borrowed_books bb " +
                        "JOIN \"mySchema\".books b ON bb.book_id = b.book_id " +
                        "JOIN \"mySchema\".readers r ON bb.reader_id = r.reader_id " +
                        "WHERE bb.status = 'borrowed'")) {
            statement.executeQuery();

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setBookId(resultSet.getInt("book_id"));
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setPublishedYear(resultSet.getInt("published_year"));
                    book.setGenre(resultSet.getString("genre"));

                    Reader reader = new Reader();
                    reader.setReaderId(resultSet.getInt("reader_id"));
                    reader.setName(resultSet.getString("name"));
                    reader.setEmail(resultSet.getString("email"));
                    reader.setPhone(resultSet.getString("phone"));

                    Date borrowDate = resultSet.getDate("borrow_date");

                    OccupiedBook occupiedBook = new OccupiedBook(book, reader, borrowDate);
                    occupiedBooks.add(occupiedBook);

                    for (OccupiedBook occupiedBook1 : occupiedBooks) {
                        Book book1 = occupiedBook1.getBook();
                        Reader reader1 = occupiedBook1.getReader();

                        System.out.printf("| %-20s | %-20s | %-10s | %-15s |%n",
                                book1.getTitle(),
                                book1.getAuthor(),
                                reader1.getName(),
                                occupiedBook1.getBorrowDate());
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                connection.close();
            }

            return occupiedBooks;
        }
    }

    @Override
    public Reader updateReader(Reader reader) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        try (PreparedStatement statement = connection.prepareStatement("UPDATE \"mySchema\".readers SET name = ?, email = ?, phone = ? WHERE reader_id = ?", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, reader.getName());
            statement.setString(2, reader.getEmail());
            statement.setString(3, reader.getPhone());
            statement.setInt(4, reader.getReaderId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }

        return reader;
    }

    @Override
    public List<Book> filterBooksByStatus(String status) throws SQLException {
        List<Book> books = new ArrayList<>();
        Connection connection = DriverManager.getConnection(url, user, password);

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT b.* FROM \"mySchema\".books b " +
                        "JOIN \"mySchema\".borrowed_books bb ON b.book_id = bb.book_id " +
                        "WHERE bb.status = ?")) {

            statement.setString(1, status);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setBookId(resultSet.getInt("book_id"));
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setPublishedYear(resultSet.getInt("published_year"));
                    book.setGenre(resultSet.getString("genre"));

                    books.add(book);

                    System.out.printf("| %-20s | %-20s | %-10d | %-15s |%n",
                            book.getTitle(),
                            book.getAuthor(),
                            book.getPublishedYear(),
                            book.getGenre());
                }
                return books;
            }
        }
    }

    @Override
    public List<Book> findBooksBorrowedAfterDate(java.util.Date date) throws SQLException {
        List<Book> books = new ArrayList<>();
        Connection connection = DriverManager.getConnection(url, user, password);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT b.book_id, b.title, b.author, b.published_year, b.genre, " +
                        "bb.borrow_date AS borrow_date " +  // Явно задаем алиас
                        "FROM \"mySchema\".books b " +
                        "JOIN \"mySchema\".borrowed_books bb ON b.book_id = bb.book_id " +
                        "WHERE bb.borrow_date > ? AND bb.status = 'borrowed'")) {

            statement.setDate(1, sqlDate);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setBookId(resultSet.getInt("book_id"));
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setPublishedYear(resultSet.getInt("published_year"));
                    book.setGenre(resultSet.getString("genre"));

                    books.add(book);

                    Date borrowDate = resultSet.getDate("borrow_date");

                    System.out.printf("| %-20s | %-20s | %-10d | %-15s | %-15s |%n",
                            book.getTitle(),
                            book.getAuthor(),
                            book.getPublishedYear(),
                            book.getGenre(),
                            borrowDate);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
        return books;
    }
}