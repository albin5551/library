package librarymanagement.library.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;

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
    
    Page<Book>getAllBook(Integer pageNo,Integer pageSize,String sortBy);
    Page<Book>getAllBookStock(Integer pageNo,Integer pageSize,String sortBy);
    BookListView delete(Integer bookId) throws NotFoundException;
    Page<Book>getAllBookStocks(String keyword, Integer pageNo,Integer pageSize,String sortBy);
    List<Object[]> getBookCountByCategory();
    HttpEntity<byte[]> getImagePic(Integer bookId);
    Page <Book>getBycategroy(List< Integer> categoryId,Integer pageNo,Integer pageSize,String sortBy);
    Page <Book>getBybookAuthorandCategory(List< String> author,List<Integer>categoryId,Integer pageNo,Integer pageSize,String sortBy);
    ArrayList<String> getAuthor();
}

