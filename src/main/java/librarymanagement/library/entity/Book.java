package librarymanagement.library.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.ru.INN;

import librarymanagement.library.form.BookForm;

@Entity
public class Book {

    public static enum Status {
        INACTIVE((byte) 0),
        ACTIVE((byte) 1);

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    private String bookName;
    private String bookAuthor;
    private Integer stock;
    private byte status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public Book() {
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    


    public Book(BookForm form){
        this.bookName=form.getBookName();
        this.bookAuthor=form.getBookAuthor();
        this.stock=form.getStock();  
        this.status = Status.ACTIVE.value;   

        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
    }

    public Book update(BookForm form){
        this.bookName=form.getBookName();
        this.bookAuthor=form.getBookAuthor();
        this.stock=form.getStock();  
        Date dt = new Date();
        this.updateDate = dt;
        return this;    
    }

     public Book delete() {
        this.status = Status.INACTIVE.value;
        return this;
     }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookId != null ? bookId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Book)) {
            return false;
        }
        return Objects.equals(bookId, ((Book) object).bookId);
    }

    @Override
    public String toString() {
        return "onlineshopping.shopping.entity.Book[ bookId=" + bookId + " ]";
    }



}
