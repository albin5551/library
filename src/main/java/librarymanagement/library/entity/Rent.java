package librarymanagement.library.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import librarymanagement.library.form.RentForm;

@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentId;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;
    // @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime rentDate;
    // @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime returnDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    private String status;
    private Long fine;
    public Rent() {

    }

    public Integer getRentId() {
        return rentId;
    }

    public void setRentId(Integer rentId) {
        this.rentId = rentId;
    }

    public User getUser() {
        return user;
    }

    public Integer getUserId() {
        return user.getUserId();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public Integer getBookId() {
        return book.getBookId();
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDateTime rentDate) {
        this.rentDate = rentDate;
    }

    public Long getFine() {
        return fine;
    }

    public void setFine(Long fine) {
        this.fine = fine;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBookName() {
        return book.getBookName();
    }

    public String getName() {
        return user.getName();
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Rent(RentForm form, User user, Book book) {
        this.user = user;
        this.book = book;
        LocalDateTime dt = LocalDateTime.now();
        Date dt1 = new Date();
        Long temp=dt1.getTime()+15*24*60*60*1000;
        Date dt15=new Date(temp);
        this.setStatus("2");
        this.rentDate = dt;
        this.dueDate = dt15;
        // this.returnDate=form.getReturDate();
    }

    public Rent update(RentForm form, User user, Book book) {
        this.user = user;
        this.book = book;
        // this.rentDate=form.getRentDate();
        LocalDateTime dt = LocalDateTime.now();
        this.returnDate = dt;
        this.setStatus("0");
        return this;
    }

    public Rent returnApprove(RentForm form, User user, Book book) {
        this.user = user;
        this.book = book;
        // this.rentDate=form.getRentDate();
        LocalDateTime dt = LocalDateTime.now();
        this.returnDate = dt;
        this.setStatus("1");
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rentId != null ? rentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Rent)) {
            return false;
        }
        return Objects.equals(rentId, ((Rent) object).rentId);
    }

    @Override
    public String toString() {
        return "librarymanagement.library.entity.Rent[ rentId=" + rentId + " ]";
    }

}
