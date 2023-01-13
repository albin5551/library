package librarymanagement.library.service;

import java.util.Collection;

import librarymanagement.library.entity.Request;
import librarymanagement.library.exception.NotFoundException;
import librarymanagement.library.form.RequestForm;
import librarymanagement.library.view.RequestListview;

public interface RequestServic {

    RequestListview add(RequestForm form);
    Collection<Request> list();
    RequestListview update(Integer reqId, RequestForm form) ;
    
}
