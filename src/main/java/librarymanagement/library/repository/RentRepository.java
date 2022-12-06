package librarymanagement.library.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import librarymanagement.library.entity.Rent;

public interface RentRepository extends Repository<Rent,Integer> {
    Collection<Rent>findAll();

    Rent save(Rent rent);

    Optional<Rent>findById(Integer rentId);

    void delete(Rent rentId);
    
}
