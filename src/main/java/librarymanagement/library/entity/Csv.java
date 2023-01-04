package librarymanagement.library.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.UniqueElements;






@Entity
@Table(name="book")

public class Csv {


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
@Column(name="book_id")
private Long bookId;


@Column(name="book_name")
private String bookName;

@Column(name="book_author")
private String bookAuthor;

@Column(name="stock")
private Integer stock;

@Column(name = "status")
private Byte status;

@Column(name = "category_id")
private Integer categoryId;

@Column(name="create_date")
@Temporal(TemporalType.TIMESTAMP)
private Date createDate;

@Column(name="update_date")
@Temporal(TemporalType.TIMESTAMP)
private Date updateDate;

public Csv(){

}

public Long getBookId() {
    return bookId;
}

public void setBookId(Long bookId) {
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

public Byte getStatus() {
    return status;
}

public void setStatus(Byte status) {
    this.status = status;
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
public Integer getCategoryId() {
    return categoryId;
}

public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
}




// public Csv(String bookName,String bookAuthor,Integer stock){
//     // this.bookId=bookId;
//     this.bookName=bookName;
//     this.bookAuthor=bookAuthor;
//     this.stock=stock;
//     this.status=Status.ACTIVE.value;
//     this.createDate=new Date();

// }


public Csv(String bookName, String bookAuthor, Integer stock,Integer categoryId) {
    this.bookName = bookName;
    this.bookAuthor = bookAuthor;
    this.stock = stock;
    this.categoryId=categoryId;
    this.status=Status.ACTIVE.value;
    Date dt = new Date();
      this.createDate=dt;

}

@Override
public String toString() {
    return "Csv [bookId=" + bookId + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", stock=" + stock
            + ", status=" + status + ", categoryId=" + categoryId + ", createDate=" + createDate + ", updateDate="
            + updateDate + "]";
}




    
}
