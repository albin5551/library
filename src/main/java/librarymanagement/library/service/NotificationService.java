package librarymanagement.library.service;

import java.util.Collection;

import librarymanagement.library.entity.Notification;
import librarymanagement.library.form.NotificationForm;
import librarymanagement.library.view.NotificationListView;

public interface NotificationService {

    NotificationListView add(NotificationForm form );
    Collection<Notification> listAll();
    void aad(NotificationForm form);
    Collection<Notification> listbyId();
    void deleteAllbyUserid();
    
}
