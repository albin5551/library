package librarymanagement.library.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import librarymanagement.library.entity.Book;
import librarymanagement.library.entity.Notification;
import librarymanagement.library.form.NotificationForm;
import librarymanagement.library.repository.BookRepository;
import librarymanagement.library.repository.NotificationRepository;
import librarymanagement.library.service.NotificationService;
import librarymanagement.library.view.NotificationListView;

@Service
public class NotifictationServiceimpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public NotificationListView add(NotificationForm form){
        Book book=bookRepository.findByBookId(form.getBookId());
        return new NotificationListView(notificationRepository.save(new Notification(form,book)));
    }

    @Override
    public Collection<Notification> listAll(){
        return notificationRepository.findAll();
    }

    


}
