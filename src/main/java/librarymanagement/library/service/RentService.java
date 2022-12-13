package librarymanagement.library.service;

import java.util.Collection;

import javax.validation.Valid;

import librarymanagement.library.entity.Rent;
import librarymanagement.library.exception.NotFoundException;
import librarymanagement.library.form.RentForm;
import librarymanagement.library.view.RentListView;

public interface RentService {

    Collection<Rent>list();
    RentListView add(RentForm form);
    RentListView get(Integer rentId)throws NotFoundException;
    RentListView update(Integer rentId,RentForm form);
    void delete(Integer rentId) throws NotFoundException;
    RentListView rentApprove(Integer rentId,  RentForm form);
    Collection<Rent> list1();
    
    
}
