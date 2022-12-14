package librarymanagement.library.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import librarymanagement.library.entity.Rent;
import librarymanagement.library.view.RentListView;

public interface RentRepository extends PagingAndSortingRepository<Rent,Integer> {
    Collection<Rent>findAll();

    Rent save(Rent rent);
    public Page<Rent>findAll(Pageable paging);

    Optional<Rent>findById(Integer rentId);

    void delete(Rent rentId);
    Collection<Rent>findAllByUserUserId(Integer userId);
    
}
