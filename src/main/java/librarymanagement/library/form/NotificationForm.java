package librarymanagement.library.form;

import javax.validation.constraints.Size;

public class NotificationForm {
    

@Size(max=255)
private String message;
private Integer bookId;
public String getMessage() {
    return message;
}

public void setMessage(String message) {
    this.message = message;
}

public Integer getBookId() {
    return bookId;
}

public void setBookId(Integer bookId) {
    this.bookId = bookId;
}

}
