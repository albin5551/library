package librarymanagement.library.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import librarymanagement.library.form.NotificationForm;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notifiId;
    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    private byte status;
    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;
    private Integer senderId;
    private Integer recipentId;
    private byte readStatus;

    public static enum Status {
        INACTIVE((byte) 0),
        ACTIVE((byte) 1);

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }
    }
    public static enum notificationStatus {
        UNREAD((byte) 0),
        READ((byte) 1);

        public final byte value;

        private notificationStatus(byte value) {
            this.value = value;
        }
    }
    public Notification() {

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

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getRecipentId() {
        return recipentId;
    }

    public void setRecipentId(Integer recipentId) {
        this.recipentId = recipentId;
    }

    public byte getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(byte readStatus) {
        this.readStatus = readStatus;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getNotifiId() {
        return notifiId;
    }

    public void setNotifiId(Integer notifiId) {
        this.notifiId = notifiId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Notification(NotificationForm form ,Book book) {
        this.message = form.getMessage();
        Date dt = new Date();
        this.createDate = dt;
        this.book=book;
        this.status = Status.ACTIVE.value;
        this.readStatus=notificationStatus.UNREAD.value;
    }


    public Notification update() {
        Date dt = new Date();
        this.readStatus=notificationStatus.READ.value;
        this.updateDate = dt;
        return this;
    }

    @Override
    public String toString() {
        return "Notification [notifiId=" + notifiId + ", message=" + message + "]";
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notifiId != null ? notifiId.hashCode() : 0);
        return hash;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Notification)) {
            return false;
        }
        return Objects.equals(notifiId, ((Notification) object).notifiId);
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

}
