import java.sql.*;
import java.util.List;

public class MainForLesson31 implements LibraryApi {
    private static String url;
    private static String user;
    private static String password;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
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

//        app.addBook(book);
//        app.updateBookStatus(5,"returned");
//        app.addReader(reader);
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
    public List<OccupiedBook> getAllOccupiedBooks() {
        return List.of();
    }

    @Override
    public Reader updateReader(Reader reader) {
        return null;
    }

    @Override
    public List<Book> filterBooksByStatus(String status) {
        return List.of();
    }

    @Override
    public List<Book> findBooksBorrowedAfterDate(java.util.Date date) {
        return List.of();
    }
}
