package librarymanagement.library.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import librarymanagement.library.entity.Notification;

public interface NotificationRepository extends Repository<Notification,Integer>{

    Notification save(Notification notification);

    Collection <Notification>findAll();
    @Query(value = "SELECT * FROM notification where recipent_id = ?1 AND read_status=0",nativeQuery=true)
    Collection<Notification>findbyrecipentId(Integer recipentId);
    Optional <Notification>findById(Integer notifiId);
  

}
