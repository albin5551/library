package librarymanagement.library.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.validator.internal.util.privilegedactions.IsClassPresent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import librarymanagement.library.entity.Book;
import librarymanagement.library.entity.Rent;
import librarymanagement.library.form.RentForm;
import librarymanagement.library.json.Json.DateFormat;
import librarymanagement.library.service.RentService;
import librarymanagement.library.service.impl.RentServiceImpl;
import librarymanagement.library.view.RentCharView;
import librarymanagement.library.view.RentListView;

@RestController
@RequestMapping("/rent")
public class RentController {

    @Autowired
    private RentService rentService;
    @Autowired
    private RentServiceImpl rentServices;
    @GetMapping
    public Collection<Rent> list() {
        return rentService.list();
    }

    @PostMapping
    public RentListView add(@Valid @RequestBody RentForm form) {
        return rentService.add(form);
    }

    @GetMapping("/{rentId}")
    public RentListView get(@PathVariable("rentId") Integer rentId) {
        return rentService.get(rentId);
    }

    @GetMapping("/list/user")
    public Collection<Rent> list1(Principal p) {
        return rentService.list1();
    }

    @PutMapping("/{rentId}")
    public RentListView update(@PathVariable("rentId") Integer rentId, @Valid @RequestBody RentForm form) {
        return rentService.update(rentId, form);
    }

    @PutMapping("/approve/{rentId}")
    public RentListView rentApprove(@PathVariable("rentId") Integer rentId, @Valid @RequestBody RentForm form) {
        return rentService.rentApprove(rentId, form);
    }

    @DeleteMapping("/{rentId}")

    public void delete(@PathVariable("rentId") Integer rentId) {
        rentService.delete(rentId);
    }

    @GetMapping("/pagenated")
    public ResponseEntity<Page<Rent>> getAllBorrow(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<Rent> list = rentService.getAllRent(pageNo - 1, pageSize, sortBy);
        return new ResponseEntity<Page<Rent>>(list, new HttpHeaders(),
                HttpStatus.OK);

    }

    @GetMapping("/export")
    public void Exportcsv(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("text/csv");
        java.text.DateFormat datefFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = datefFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        httpServletResponse.setHeader(headerKey, headerValue);
        List<Rent> rents = rentService.listcsv();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(httpServletResponse.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = { "rent_id", "username", "bookname", "rent_date", "return_date", "status" };
        String[] nameMapping = { "rentId", "name", "bookName", "rentDate", "returnDate", "status" };
        csvWriter.writeHeader(csvHeader);
        for (Rent rent : rents) {
            // System.out.println("         :"+rent.getStatus());
            if(rent.getStatus().matches("1")){
                // System.out.println("         :"+rent.getStatus());
                    rent.setStatus("Approved");

            }
            else{
                rent.setStatus("Processing");
            }
            csvWriter.write(rent, nameMapping);
        }
        csvWriter.flush();
        csvWriter.close();
    }

    @GetMapping("/search/pagenateds")
    public ResponseEntity<Page<Rent>> getAllBookStockSearch(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<Rent> list = rentService.getAllRentKey(keyword, pageNo - 1, pageSize, sortBy);
        return new ResponseEntity<Page<Rent>>(list, new HttpHeaders(), HttpStatus.OK);

    }


    @GetMapping("/search/export/{key}")
    public void exportSearchcsv(HttpServletResponse httpServletResponse,@PathVariable String key) throws IOException {
        httpServletResponse.setContentType("text/csv");
        java.text.DateFormat datefFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = datefFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        httpServletResponse.setHeader(headerKey, headerValue);
        List<Rent> rents = rentService.listCsvSerach(key);

        ICsvBeanWriter csvWriter = new CsvBeanWriter(httpServletResponse.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = { "rent_id", "username", "bookname", "rent_date", "return_date", "status" };
        String[] nameMapping = { "rentId", "name", "bookName", "rentDate", "returnDate", "status" };
        csvWriter.writeHeader(csvHeader);
        for (Rent rent : rents) {
            // System.out.println("         :"+rent.getStatus());
            if(rent.getStatus().matches("1")){
                // System.out.println("         :"+rent.getStatus());
                    rent.setStatus("Approved");

            }
            else{
                rent.setStatus("Processing");
            }
            csvWriter.write(rent, nameMapping);
        }
        csvWriter.flush();
        csvWriter.close();
    }
// @GetMapping("/chart")
//     public List<Rent>chart(){
//         return rentService.listBetweenDates();
//     }
@GetMapping("/chart") //chart
    public RentCharView getchart(){
        RentCharView test =rentServices.getChart();
        return test;
    }

    

}
