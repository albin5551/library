package librarymanagement.library.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import librarymanagement.library.entity.Book;
import librarymanagement.library.entity.Category;
import librarymanagement.library.exception.NotFoundException;
import librarymanagement.library.form.BookForm;
import librarymanagement.library.repository.BookRepository;
import librarymanagement.library.repository.CategoryRepository;
import librarymanagement.library.security.util.FileUpload;
import librarymanagement.library.service.BookService;
import librarymanagement.library.view.BookListView;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;

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
        Category category = categoryRepository.findByCategoryId(form.getCategoryId());
        return new BookListView(bookRepository.save(new Book(form, category)));

    }

    @Override
    public BookListView get(Integer bookId) throws NotFoundException {
        return bookRepository.findById(bookId).map((book)->{
            return new BookListView(book);
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public BookListView update(Integer bookId, BookForm form) throws NotFoundException {
        return bookRepository.findById(bookId)
                .map((book) -> {
                    Category category = categoryRepository.findByCategoryId(form.getCategoryId());
                    return new BookListView(bookRepository.save(book.update(form, category)));
                }).orElseThrow(NotFoundException::new);
    }

    // @Override
    // @Transactional
    // public void delete(Integer bookId) throws NotFoundException {
    // bookRepository.delete(bookRepository.findById(bookId).orElseThrow());
    // }

    @Override
    public Page<Book> getAllBook(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Book> pagedResult = bookRepository.findAll(paging);
        // if (pagedResult.hasContent()){
        // return pagedResult.getContent();
        // }else{
        // return new ArrayList<Book>();
        // }
        return pagedResult;
    }

    @Override
    public Page<Book> getAllBookStock(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Book> pagedResult = bookRepository.findAllStockNative(paging);
        return pagedResult;
        // if (pagedResult.hasContent()){
        // return pagedResult.getContent();
        // }else{
        // return new ArrayList<Book>();
        // }
    }

    @Override
    public Page<Book> getAllBookStocks(String keyword, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        System.out.println(keyword);
        String k = keyword;
        String k1 = keyword;
        String k2 = keyword;
        Page<Book> pagedResult = bookRepository.findByKeywords(keyword, k, k1, k2, paging);
        // System.out.println(pagedResult.getTotalElements());
        return pagedResult;
        // Long p= pagedResult.getTotalElements();
        // System.out.println(p);
        // if (pagedResult.hasContent()){
        // return pagedResult.getContent();
        // }else{
        // return new ArrayList<Book>();
        // }
    }

    @Override
    @Transactional
    public BookListView delete(Integer bookId) throws NotFoundException {
        Book book = bookRepository.findById(bookId).get();
        // User user= userRepository.findById(currentUser.getUserId()).get();
        return new BookListView(bookRepository.save(book.delete()));
    }

    @Override
    public List<Object[]> getBookCountByCategory() {
        return bookRepository.findcountByCategory();
    }

    // ImageUpload Contents
    @Override
    public HttpEntity<byte[]> getImagePic(Integer bookId) {

        String image = bookRepository.findByBookId(bookId).getImage();

        byte[] file = FileUpload.getImage(image);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(file.length);

        return new HttpEntity<>(file, headers);

    }

    @Override
    public Page<Book> getBycategroy(List<Integer> categoryId, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Book> pagedResult = bookRepository.findByCategoryCategoryId(categoryId, paging);
        return pagedResult;
    }

    public Page<Book> getBybookAuthorandCategory(List<String> author, List<Integer> categoryId, Integer pageNo,
            Integer pageSize,
            String sortBy) {
        Page<Book> pagedResult;
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        if (author.size() > 0 && categoryId.size() == 0) {

            pagedResult = bookRepository.findBybookAuthor(author, paging);
        } else if (author.size() == 0 && categoryId.size() > 0) {
            pagedResult = bookRepository.findByCategoryCategoryId(categoryId, paging);
        } else {
            pagedResult = bookRepository.findByAuthorandCategory(categoryId, author, paging);

        }
        return pagedResult;
    }

    public Page<Book> UsergetBybookAuthorandCategory(List<String> author, List<Integer> categoryId, Integer pageNo,
            Integer pageSize,
            String sortBy) {
        Page<Book> pagedResult;
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        if(author.size() == 0 && categoryId.size() == 0){
            pagedResult=bookRepository.findAllStockNative(paging);
        }
        else if (author.size() > 0 && categoryId.size() == 0) {

            pagedResult = bookRepository.UfindBybookAuthor(author, paging);
        } else if (author.size() == 0 && categoryId.size() > 0) {
            pagedResult = bookRepository.UfindByCategoryCategoryId(categoryId, paging);
        } else {
            pagedResult = bookRepository.UfindByAuthorandCategory(categoryId, author, paging);

        }
        return pagedResult;
    }

    @Override
    public ArrayList<String> getAuthor() {
        return bookRepository.AuthorName();
    }

}
