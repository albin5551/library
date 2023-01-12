package librarymanagement.library.form;

import javax.validation.constraints.Size;

public class NotificationForm {
    

@Size(max=255)
private String message;

public String getMessage() {
    return message;
}

public void setMessage(String message) {
    this.message = message;
}

}
