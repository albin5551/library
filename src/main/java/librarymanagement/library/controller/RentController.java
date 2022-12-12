package librarymanagement.library.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PutMapping("/{rentId}")
    public RentListView update(@PathVariable("rentId") Integer rentId,@Valid @RequestBody RentForm form){
        return rentService.update(rentId, form);
    }

    @DeleteMapping("/{rentId}")

    public void delete(@PathVariable("rentId")Integer rentId){
        rentService.delete(rentId);
    }
    

    
}
