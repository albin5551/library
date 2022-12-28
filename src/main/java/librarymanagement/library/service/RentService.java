package librarymanagement.library.service;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import librarymanagement.library.entity.Rent;
import librarymanagement.library.exception.NotFoundException;
import librarymanagement.library.form.RentForm;
import librarymanagement.library.view.RentListView;

public interface RentService {

    Collection<Rent>list();
    List<Rent>listcsv();
    RentListView add(RentForm form);
    RentListView get(Integer rentId)throws NotFoundException;
    RentListView update(Integer rentId,RentForm form);
    void delete(Integer rentId) throws NotFoundException;
    RentListView rentApprove(Integer rentId,  RentForm form);
    Collection<Rent> list1();
    List<Rent>getAllRent(Integer pageNo,Integer pageSize,String sortBy);
    Page<Rent>getAllRentKey(String keyword,Integer pageNo,Integer pageSize,String sortBy);
    
    
    
}
