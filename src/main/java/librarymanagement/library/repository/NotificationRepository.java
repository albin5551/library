package librarymanagement.library.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import librarymanagement.library.entity.Notification;

public interface NotificationRepository extends Repository<Notification,Integer>{

    Notification save(Notification notification);

    Collection <Notification>findAll();
}
