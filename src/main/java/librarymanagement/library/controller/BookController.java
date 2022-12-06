package librarymanagement.library.controller;

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
import librarymanagement.library.form.BookForm;
import librarymanagement.library.service.BookService;
import librarymanagement.library.view.BookListView;
import lombok.val;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

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

    @GetMapping("/pagenated/")
    public ResponseEntity<List<Book>>getAllBorrow(
                        @RequestParam(defaultValue = "0") Integer pageNo,
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Book> list = bookService.getAllBook(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Book>>(list,new HttpHeaders(),
        HttpStatus.OK);

    }
}
