package librarymanagement.library.repository;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;
// import org.springframework.stereotype.Repository;

import librarymanagement.library.entity.Book;


public interface BookRepository extends PagingAndSortingRepository <Book,Integer> {
    Collection<Book>findAll();

    Book save(Book book);

    Optional<Book> findById(Integer bookId);

    void delete(Book bookId);

    Book findByBookId(Integer bookId);
    
    public Page<Book>findAll(Pageable paging);

    @Query(value = "SELECT * FROM book where stock !=0",nativeQuery = true)
    public  Page<Book> findAllStockNative(Pageable paging);

}
