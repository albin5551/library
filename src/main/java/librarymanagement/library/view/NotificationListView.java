package librarymanagement.library.view;
import java.util.Date;

import librarymanagement.library.entity.Notification;
import librarymanagement.library.json.Json;
public class NotificationListView {
    private final Integer notifiId;
    private final String message;
    @Json.DateTimeFormat
    private final Date createDate;
    @Json.DateTimeFormat
    private final Date updateDate;
    private final byte status;
    private final BookListView bookId;



    public NotificationListView(Notification notification){
        this.notifiId=notification.getNotifiId();
        this.message=notification.getMessage();
        this.createDate=notification.getCreateDate();
        this.updateDate=notification.getUpdateDate();
        this.status=notification.getStatus();
        this.bookId=new BookListView(notification.getBook());

    }
    public Integer getNotifiId() {
        return notifiId;
    }
    public String getMessage() {
        return message;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public byte getStatus() {
        return status;
    }
    public BookListView getBookId() {
        return bookId;
    }
    


    
    
}
