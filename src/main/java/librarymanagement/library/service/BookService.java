package librarymanagement.library.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import librarymanagement.library.entity.Book;
import librarymanagement.library.exception.NotFoundException;
import librarymanagement.library.form.BookForm;
import librarymanagement.library.view.BookListView;

public interface BookService {
    
    Collection<Book>list(); 

    BookListView add(BookForm form);

    
    BookListView get(Integer bookId)throws NotFoundException;

    // void delete(Integer bookId)throws NotFoundException;

    BookListView update(Integer bookId,BookForm form)throws NotFoundException;
    
    List<Book>getAllBook(Integer pageNo,Integer pageSize,String sortBy);
    List<Book>getAllBookStock(Integer pageNo,Integer pageSize,String sortBy);
    BookListView delete(Integer bookId) throws NotFoundException;
}
