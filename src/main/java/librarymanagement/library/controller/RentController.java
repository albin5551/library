package librarymanagement.library.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import librarymanagement.library.entity.Book;
import librarymanagement.library.entity.Rent;
import librarymanagement.library.form.RentForm;
import librarymanagement.library.service.RentService;
import librarymanagement.library.view.RentListView;

@RestController
@RequestMapping("/rent")
public class RentController {

    @Autowired
    private RentService rentService;

    @GetMapping
    public Collection<Rent>list(){
        return rentService.list();
    }

    @PostMapping
    public RentListView add(@Valid @RequestBody RentForm form){
        return rentService.add(form);
    }

    @GetMapping("/{rentId}")
    public RentListView get(@PathVariable("rentId")Integer rentId){
        return rentService.get(rentId);
    }
    @GetMapping("/list/user")
        public Collection<Rent>list1(Principal p){
        return  rentService.list1();
    }

    @PutMapping("/{rentId}")
    public RentListView update(@PathVariable("rentId") Integer rentId,@Valid @RequestBody RentForm form){
        return rentService.update(rentId, form);
    }

    @PutMapping("/approve/{rentId}")
    public RentListView rentApprove(@PathVariable("rentId") Integer rentId,@Valid @RequestBody RentForm form){
        return rentService.rentApprove(rentId, form);
    }

    @DeleteMapping("/{rentId}")

    public void delete(@PathVariable("rentId")Integer rentId){
        rentService.delete(rentId);
    }

    @GetMapping("/pagenated")
    public ResponseEntity<List<Rent>>getAllBorrow(
                        @RequestParam(defaultValue = "0") Integer pageNo,
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Rent> list = rentService.getAllRent(pageNo-1, pageSize, sortBy);
        return new ResponseEntity<List<Rent>>(list,new HttpHeaders(),
        HttpStatus.OK);

    }
    

    
}
