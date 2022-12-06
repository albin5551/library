package librarymanagement.library.repository;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import librarymanagement.library.entity.Book;

public interface BookRepository extends PagingAndSortingRepository <Book,Integer> {
    Collection<Book>findAll();

    Book save(Book book);

    Optional<Book> findById(Integer bookId);

    void delete(Book bookId);

    Book findByBookId(Integer bookId);
    
    public Page<Book>findAll(Pageable paging);
}
