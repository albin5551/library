package librarymanagement.library.form;

import javax.validation.constraints.Size;

public class NotificationForm {
    

@Size(max=255)
private String message;
private Integer bookId;
// private Integer senderId;
// private Integer recipentId;
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

// public Integer getSenderId() {
//     return senderId;
// }

// public void setSenderId(Integer senderId) {
//     this.senderId = senderId;
// }

// public Integer getRecipentId() {
//     return recipentId;
// }

// public void setRecipentId(Integer recipentId) {
//     this.recipentId = recipentId;
// }

}
