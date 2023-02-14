package librarymanagement.library.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.Repository;

import librarymanagement.library.entity.Book;

public interface BookRepository extends Repository<Book, Integer> {

    // @Query(value = "SELECT * FROM book where status = 1",nativeQuery = true)
    Collection<Book> findAll();

    Book save(Book book);

    Optional<Book> findById(Integer bookId);

    void delete(Book bookId);

    Book findByBookId(Integer bookId);

    @Query(value = "SELECT * FROM book where  status = 1", nativeQuery = true)
    public Page<Book> findAll(Pageable paging);

    @Query(value = "SELECT * FROM book  where stock !=0 AND status = 1", nativeQuery = true)
    public Page<Book> findAllStockNative(Pageable paging);



    @Query(value = "Select * from book where  status = 1 AND book_name like %?1% order by book_name like ?2% DESC,book_name like %?3 DESC,book_name like %?4% ", nativeQuery = true)
    public Page<Book> findByKeywords(String keyword, String k, String k1, String k2, Pageable pageable);

    @Query(value = "select COUNT(book.book_id),category.category_name from book Inner Join category on book.category_id=category.category_id group by book.category_id ", nativeQuery = true)


    public List<Object[]> findcountByCategory();


    @Query(value = "Select * from book where status = 1 AND category_id in(:categoryId)", nativeQuery = true)
    Page<Book> findByCategoryCategoryId(List<Integer> categoryId, Pageable pageable);

    @Query(value = "Select * from book where status = 1 AND stock !=0 AND category_id in(:categoryId)", nativeQuery = true)
    Page<Book> UfindByCategoryCategoryId(List<Integer> categoryId, Pageable pageable);

    @Query(value = "Select * from book where status = 1 AND book_author in (?1)", nativeQuery = true)
    Page<Book> findBybookAuthor(List<String> author, Pageable pageable);

    @Query(value = "Select * from book where status = 1 AND stock !=0 AND book_author in (?1)", nativeQuery = true)
    Page<Book> UfindBybookAuthor(List<String> author, Pageable pageable);

    @Query(value = "Select distinct (book_author) from book", nativeQuery = true)
    ArrayList<String> AuthorName();


    @Query(value = "select * from book where status =1 AND category_id in(?1)and book_author in(?2)", nativeQuery = true)
    Page<Book> findByAuthorandCategory(List<Integer> categoryId, List<String> author, Pageable pageable);

    @Query(value = "select * from book where status =1 AND stock !=0 AND category_id in(?1)and book_author in(?2)", nativeQuery = true)
    Page<Book> UfindByAuthorandCategory(List<Integer> categoryId, List<String> author, Pageable pageable);
}
