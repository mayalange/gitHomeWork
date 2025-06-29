import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface LibraryApi {
    Book addBook(Book book) throws SQLException;

    Book updateBookStatus(int bookId, String status) throws SQLException;

    Reader addReader(Reader reader) throws SQLException;

    List<OccupiedBook> getAllOccupiedBooks() throws SQLException;

    Reader updateReader(Reader reader) throws SQLException;

    List<Book> filterBooksByStatus(String status) throws SQLException;

    List<Book> findBooksBorrowedAfterDate(Date date) throws SQLException;
}
