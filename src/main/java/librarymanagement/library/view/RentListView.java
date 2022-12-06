package librarymanagement.library.view;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import librarymanagement.library.entity.Rent;
import librarymanagement.library.entity.User;

public class RentListView {

private final Integer rentId;
private final UserView userId;
private final BookListView bookId;
@Temporal(TemporalType.TIMESTAMP)
private Date rentDate;
@Temporal(TemporalType.TIMESTAMP)
private Date returnDate;



public RentListView(Rent rent){
    this.rentId=rent.getRentId();
    this.userId=new UserView(rent.getUser());
    this.bookId=new BookListView(rent.getBook());
    this.rentDate=rent.getRentDate();
    this.returnDate=rent.getReturnDate();
}
    


public Integer getRentId() {
    return rentId;
}
public UserView getUserId() {
    return userId;
}
public BookListView getBookId() {
    return bookId;
}
public Date getRentDate() {
    return rentDate;
}
public Date getReturnDate() {
    return returnDate;
}




    
}
