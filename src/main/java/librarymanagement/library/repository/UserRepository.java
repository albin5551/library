package librarymanagement.library.repository;

import librarymanagement.library.entity.User;
import java.util.Collection;
import java.util.Optional;
 import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Integer> {

    Optional<User> findById(Integer userId);

    Optional<User> findByUserIdAndPassword(Integer userId, String password);
    // Optional<User> findByUserId(Integer userId);

    Optional<User> findByEmail(String email);

    User save(User user);

    void delete(User userId);

    Collection<User> findAll();
}
