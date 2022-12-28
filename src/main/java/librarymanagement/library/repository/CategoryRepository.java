package librarymanagement.library.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import librarymanagement.library.entity.Category;

public interface CategoryRepository extends Repository<Category,Integer> {


    Collection<Category> findAll();
    
    Category save(Category category);

    Optional<Category> findById(Integer categoryId);

    void delete(Category categoryId);

    Category findByCategoryId(Integer categoryId);
    
}
