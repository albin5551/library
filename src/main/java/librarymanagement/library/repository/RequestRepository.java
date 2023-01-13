package librarymanagement.library.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import librarymanagement.library.entity.Request;

public interface RequestRepository extends Repository<Request,Integer> {

    Request save(Request request);
    Collection<Request> findAll();
    Optional<Request> findById(Integer requestId);
}
