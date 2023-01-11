package librarymanagement.library.view;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import librarymanagement.library.entity.Rent;
import librarymanagement.library.entity.User;

public class RentListView {

    private final Integer rentId;
    private final UserView userId;
    private final BookListView bookId;
    // @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime rentDate;
    // @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime returnDate;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    private Long fine;
    private Long dueDays;

    public RentListView(Rent rent) {
        this.rentId = rent.getRentId();
        this.userId = new UserView(rent.getUser());
        this.bookId = new BookListView(rent.getBook());
        this.rentDate = rent.getRentDate();
        this.returnDate = rent.getReturnDate();
        this.dueDate=rent.getDueDate();
        this.status = rent.getStatus();
        this.fine=rent.getFine();
        this.dueDays=rent.getDueDays();

    }

    public Integer getRentId() {
        return rentId;
    }

    public UserView getUserId() {
        return userId;
    }

    public Long getDueDays() {
        return dueDays;
    }

    public BookListView getBookId() {
        return bookId;
    }

    public LocalDateTime getRentDate() {
        return rentDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public String getStatus() {
        return status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Long getFine() {
        return fine;
    }

}
