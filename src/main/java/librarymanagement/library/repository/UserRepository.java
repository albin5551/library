package librarymanagement.library.repository;

import librarymanagement.library.entity.User;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Integer> {

    Optional<User> findById(Integer userId);

    @Query(value = "select * from user where user_id=?1", nativeQuery = true)
    User findbyuserId(Integer userId);

    Optional<User> findByUserIdAndPassword(Integer userId, String password);
    // Optional<User> findByUserId(Integer userId);

    Optional<User> findByEmail(String email);

    User save(User user);

    void delete(User userId);

    // Collection<User>findbyStatus();

    Collection<User> findAll();

    @Query(value = "SELECT * FROM user WHERE email=?", nativeQuery = true)
    User findByEmailId(String email);
}
