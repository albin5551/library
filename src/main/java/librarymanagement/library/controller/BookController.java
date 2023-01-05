package librarymanagement.library.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
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
import org.springframework.web.multipart.MultipartFile;

import librarymanagement.library.entity.Book;
import librarymanagement.library.form.BookForm;
import librarymanagement.library.repository.BookRepository;
import librarymanagement.library.security.util.FileUpload;
import librarymanagement.library.service.BookService;
import librarymanagement.library.view.BookListView;
import lombok.val;

import org.springframework.util.StringUtils;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @GetMapping
    public Collection<Book> list() {
        return bookService.list();
    }

    @PostMapping
    public BookListView add(@Valid @RequestBody BookForm form) {
        return bookService.add(form);
    }

    @GetMapping("/{bookId}")
    public BookListView get(@PathVariable("bookId") Integer bookId) {
        return bookService.get(bookId);
    }

    @PutMapping("/{bookId}")
    public BookListView update(@PathVariable("bookId")Integer bookId, @Valid @RequestBody BookForm form){
        return bookService.update(bookId, form);
    }
    @DeleteMapping("/{bookId}")
    public void delete(@PathVariable("bookId")Integer bookId){
        bookService.delete(bookId);
    }

    @GetMapping("/pagenated")
    public ResponseEntity<Page<Book>>getAllBorrow(
                        @RequestParam(defaultValue = "0") Integer pageNo,
                        @RequestParam(defaultValue = "5") Integer pageSize,
                        @RequestParam(defaultValue = "book_id") String sortBy)
    {
        Page<Book> list = bookService.getAllBook(pageNo-1, pageSize, sortBy);
        return new ResponseEntity<Page<Book>>(list,new HttpHeaders(),
        HttpStatus.OK);

    }


    @GetMapping("/pagenateds")
    public ResponseEntity<Page<Book>>getAllBookStocks(
                        @RequestParam(defaultValue = "0") Integer pageNo,
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy)
    {
        Page<Book> list = bookService.getAllBookStock(pageNo-1, pageSize, sortBy);
        return new ResponseEntity<Page<Book>>(list,new HttpHeaders(),
        HttpStatus.OK);

    }
    @GetMapping("/search/pagenateds")
    public ResponseEntity<Page<Book>>getAllBookStockSearch(
                        @RequestParam (defaultValue = "")String keyword,
                        @RequestParam(defaultValue = "1") Integer pageNo,
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy)
    {
        System.out.println("paage size"+pageSize);
        Page<Book> list = bookService.getAllBookStocks(keyword,pageNo-1,pageSize,sortBy);
        return new ResponseEntity<Page<Book>>(list,new HttpHeaders(),
        HttpStatus.OK);
        
    }
    @GetMapping("/bycategory")
    public List<Object[]> getcountByCategory(){
        return bookService.getBookCountByCategory();
    }
    //ImageUpload Controller

    @PostMapping("/save/image/{bookId}")
    public void saveBookImage(@RequestParam(value="image" )  MultipartFile multipartFile,
    @PathVariable Integer bookId) throws IOException {

        Book book = bookRepository.findByBookId(bookId);

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        book.setImage(fileName);

        bookRepository.save(book);

    //  String UploadDir = "userProfile-photos/" + savedUserProfile.getUserprofileId();

        FileUpload.saveBookImage(fileName, multipartFile);

    }
     //Image View Controller

     @GetMapping("/image/{bookId}")
     public HttpEntity<byte[]> getImagePic(@PathVariable Integer bookId) {
 
         return bookService.getImagePic(bookId);
     }
 
    
}
