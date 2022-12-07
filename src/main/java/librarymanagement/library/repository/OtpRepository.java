package librarymanagement.library.repository;

import java.util.Collection;

import org.springframework.data.repository.Repository;

import librarymanagement.library.entity.Otp;

public interface OtpRepository extends Repository<Otp,Integer> {
    

    Otp save( Otp otp);
    void deleteAll();
    Collection<Otp>findAll();

    Otp findByEmail(String email);
}
