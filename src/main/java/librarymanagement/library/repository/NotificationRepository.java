package librarymanagement.library.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import librarymanagement.library.entity.Notification;

public interface NotificationRepository extends Repository<Notification,Integer>{

    Notification save(Notification notification);

    Collection <Notification>findAll();
    @Query(value = "SELECT * FROM notification where recipent_id = ?1 AND read_status=0",nativeQuery=true)
    Collection<Notification>findbyrecipentId(Integer recipentId);
    // @Query(value = "SELECT * FROM notification where recipent_id = ?1 ",nativeQuery=true)
    // Optional<Notification>findbyrecipentIds(Integer recipentId);
    Optional <Notification>findById(Integer notifiId);
  

}
