import java.util.Date;

public class OccupiedBook {
    private Book book;
    private Reader reader;
    private Date borrowDate;

    public OccupiedBook(Book book, Reader reader, Date borrowDate) {
        this.book = book;
        this.reader = reader;
        this.borrowDate = borrowDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }
}
