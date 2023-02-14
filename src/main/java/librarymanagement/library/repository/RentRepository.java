package librarymanagement.library.repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.Repository;

import librarymanagement.library.entity.Rent;

public interface RentRepository extends Repository<Rent, Integer> {
    Collection<Rent> findAll();

    @Query(value = "SELECT * FROM rent", nativeQuery = true)
    List<Rent> findAllC();

    Rent save(Rent rent);

    public Page<Rent> findAll(Pageable paging);

    Optional<Rent> findById(Integer rentId);

    void delete(Rent rentId);

    Collection<Rent> findAllByUserUserId(Integer userId);

    @Query(value = "select * from rent where user_id in(select user_id from user where name like %?1% )", nativeQuery = true)
    Page<Rent> findByKey(String keyword, Pageable paging);

    @Query(value = "select * from rent where user_id in(select user_id from user where name like %?1% )", nativeQuery = true)
    List<Rent> findBySer(String keyword);


    @Query(value = "SELECT * FROM rent WHERE  rent_date BETWEEN ?1 and ?2 AND return_date between ?1 and ?2 ", nativeQuery = true)
    List<Rent> findBybetweenDate(LocalDateTime st, LocalDateTime ed);


    @Query(value = "select * from rent where rent_id in(select rent_id  from rent where CAST(due_date AS date)=date_add(curdate(),interval 1 day) and (status=2 or status=0) )", nativeQuery = true)
    Collection<Rent>findbyDueDate();
    @Query(value = "select * from rent where rent_id in(select rent_id  from rent where CAST(due_date AS date) < curdate() and (status=2 or status=0 ));", nativeQuery = true)
    Collection<Rent>findbyDueDateFine();



}
