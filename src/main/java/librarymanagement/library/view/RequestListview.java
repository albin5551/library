package librarymanagement.library.view;

import librarymanagement.library.entity.Request;

public class RequestListview {
    private final Integer requestId;
    private final BookListView book;
    private final UserView user;
    private final Byte status;



    public RequestListview(Request request){
        this.requestId=request.getRequestId();
        this.book=new BookListView(request.getBook());
        this.user=new UserView(request.getUser());
        this.status=request.getStatus();
    }

    public Integer getRequestId() {
        return requestId;
    }
    public BookListView getBookId() {
        return book;
    }
    public UserView getUserId() {
        return user;
    }

    public Byte getStatus() {
        return status;
    }


}
