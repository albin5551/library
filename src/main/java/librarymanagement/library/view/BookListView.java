package librarymanagement.library.view;

import librarymanagement.library.entity.Book;
import librarymanagement.library.json.Json;
import java.util.Date;

public class BookListView {

    private final int bookId;
    private String bookName;
    private String bookAuthor;
    private Integer stock;
    @Json.DateTimeFormat
    private final Date createDate;
    @Json.DateTimeFormat
    private final Date updateDate;

    public BookListView(Book book) {
        this.bookId = book.getBookId();
        this.bookName = book.getBookName();
        this.bookAuthor = book.getBookAuthor();
        this.stock = book.getStock();
        this.createDate = book.getCreateDate();
        this.updateDate = book.getUpdateDate();
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public Integer getStock() {
        return stock;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

}
