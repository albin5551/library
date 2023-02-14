package librarymanagement.library.entity;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Request {
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
    private Integer requestId;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    private byte status;


    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Request() {

    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Request(User user, Book book) {
        this.book = book;
        this.user = user;
        Date dt = new Date();
        this.createDate = dt;
        this.status=Status.ACTIVE.value;
    }

    public Request update(User user, Book book) {
        this.book = book;
        this.user = user;
        Date dt = new Date();
        this.updateDate = dt;
        this.status=Status.INACTIVE.value;
        return this;
    }

 

    @Override
    public String toString() {
        return "Request [requestId=" + requestId + ", user=" + user + ", book=" + book + ", createDate=" + createDate
                + ", updateDate=" + updateDate + ", status=" + status + "]";
    }

    public boolean equals(Object object) {
        if (!(object instanceof Request)) {
            return false;
        }
        return Objects.equals(requestId, ((Request) object).requestId);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requestId != null ? requestId.hashCode() : 0);
        return hash;
    }

    public Optional<User> map(Object object) {
        return null;
    }

}
