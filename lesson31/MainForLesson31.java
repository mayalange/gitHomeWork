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

        Book addedBook = app.addBook(book);
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
        try (PreparedStatement statement = connection.prepareStatement("UPDATE borrowed_books SET status = ?, return_date = ? WHERE book_id = ? AND status = 'borrowed'")) {
            statement.setString(1, status);
            statement.setString(1, status);
            statement.setDate(2, new Date(System.currentTimeMillis()));
            statement.setInt(3, bookId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
        return null;
    }

    @Override
    public Reader addReader(Reader reader) {
        return null;
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
