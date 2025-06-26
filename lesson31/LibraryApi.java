import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface LibraryApi {
    Book addBook(Book book) throws SQLException;

    Book updateBookStatus(int bookId, String status) throws SQLException;

    Reader addReader(Reader reader);

    List<OccupiedBook> getAllOccupiedBooks();

    Reader updateReader(Reader reader);

    List<Book> filterBooksByStatus(String status);

    List<Book> findBooksBorrowedAfterDate(Date date);
}
