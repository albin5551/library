package librarymanagement.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import librarymanagement.library.entity.Csv;

@Repository
public interface CsvRepository extends JpaRepository<Csv,Integer> {
    
}
