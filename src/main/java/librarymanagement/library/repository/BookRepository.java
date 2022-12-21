package librarymanagement.library.repository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;
// import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import librarymanagement.library.entity.Book;


public interface BookRepository extends PagingAndSortingRepository <Book,Integer> {
    
    // @Query(value = "SELECT * FROM book where status = 1",nativeQuery = true)
    Collection<Book>findAll();

    Book save(Book book);

    Optional<Book> findById(Integer bookId);

    void delete(Book bookId);

    Book findByBookId(Integer bookId);

    
    @Query(value = "SELECT * FROM book",nativeQuery = true)
    public Page<Book>findAll(Pageable paging);



    @Query(value = "SELECT * FROM book s where s.stock !=0 AND s.status = 1",nativeQuery = true)
    public  Page<Book> findAllStockNative(Pageable paging);

//     @Query(value = "select * from book s where s.book_name like %:keyword% ", nativeQuery = true)
//  List<Book> findByKeyword(@Param("keyword") String keyword);
   
    // @Query(value = "update book set stock=stock-1 where book_id=?1", nativeQuery = true)
    // public void updateStock(Integer bookId);
    

    @Query(value = "SELECT * FROM book  where stock !=0 AND status = 1 AND book_name like %?1%",nativeQuery = true)
   public Page<Book> findByKeywords( String keyword,Pageable pageable);
}
