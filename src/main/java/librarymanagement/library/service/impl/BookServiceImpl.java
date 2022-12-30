package librarymanagement.library.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import librarymanagement.library.entity.Book;
import librarymanagement.library.entity.Category;
import librarymanagement.library.exception.NotFoundException;
import librarymanagement.library.form.BookForm;
import librarymanagement.library.repository.BookRepository;
import librarymanagement.library.repository.CategoryRepository;
import librarymanagement.library.service.BookService;
import librarymanagement.library.view.BookListView;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Collection<Book> list() {
        return bookRepository.findAll();
    }

    @Override
    public BookListView add(BookForm form) {
        Category category=categoryRepository.findByCategoryId(form.getCategoryId());
        return new BookListView(bookRepository.save(new Book(form,category)));

    }

    @Override
    public BookListView get(Integer bookId) throws NotFoundException {
        return bookRepository.findById(bookId).map((book) -> {
            return new BookListView(book);
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public BookListView update(Integer bookId, BookForm form) throws NotFoundException {
        return bookRepository.findById(bookId)
                .map((book) -> {
                    return new BookListView(bookRepository.save(book.update(form)));
                }).orElseThrow(NotFoundException::new);
    }

    // @Override
    // @Transactional
    // public void delete(Integer bookId) throws NotFoundException {
    //     bookRepository.delete(bookRepository.findById(bookId).orElseThrow());
    // }


    @Override
    public Page<Book>getAllBook(Integer pageNo,Integer pageSize,String sortBy){
            Pageable paging=PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
            Page<Book> pagedResult=bookRepository.findAll(paging);
            // if (pagedResult.hasContent()){
            //     return pagedResult.getContent();
            // }else{
            //     return new ArrayList<Book>();
            // }
            return pagedResult; 
    }

    @Override
    public Page<Book>getAllBookStock(Integer pageNo,Integer pageSize,String sortBy){
        Pageable paging=PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
        Page<Book> pagedResult=bookRepository.findAllStockNative(paging);
        return pagedResult;
        // if (pagedResult.hasContent()){
        //     return pagedResult.getContent();
        // }else{
        //     return new ArrayList<Book>();
        // }
    }

    @Override
        public Page<Book>getAllBookStocks(String keyword, Integer pageNo,Integer pageSize,String sortBy){
            Pageable paging=PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
            System.out.println(keyword);
            String k=keyword;
            String k1=keyword;
            String k2=keyword;
            Page<Book> pagedResult=bookRepository.findByKeywords(keyword,k,k1,k2,paging);
            // System.out.println(pagedResult.getTotalElements());
            return pagedResult;
            //   Long p= pagedResult.getTotalElements();
            //   System.out.println(p);
            // if (pagedResult.hasContent()){
            //     return pagedResult.getContent();
            // }else{
            //     return new ArrayList<Book>();
            // }
}

@Override
@Transactional
public BookListView delete(Integer bookId) throws NotFoundException {
    Book book=bookRepository.findById(bookId).get();  
    // User user= userRepository.findById(currentUser.getUserId()).get();
            return new BookListView(bookRepository.save(book.delete()));
}


@Override
public List<Object[]> getBookCountByCategory(){
    return bookRepository.findcountByCategory();
}



}
