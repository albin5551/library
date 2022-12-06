package librarymanagement.library.form;

import java.sql.Date;

public class RentForm {

    private Integer userId;
    private Integer bookId;
    private Date rentDate;
    private Date returDate;

    
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getBookId() {
        return bookId;
    }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public Date getRentDate() {
        return rentDate;
    }
    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }
    public Date getReturDate() {
        return returDate;
    }
    public void setReturDate(Date returDate) {
        this.returDate = returDate;
    }



    
}
