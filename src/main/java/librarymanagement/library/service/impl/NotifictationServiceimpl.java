package librarymanagement.library.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import librarymanagement.library.entity.Book;
import librarymanagement.library.entity.Notification;
import librarymanagement.library.entity.User;
import librarymanagement.library.exception.NotFoundException;
import librarymanagement.library.form.NotificationForm;
import librarymanagement.library.repository.BookRepository;
import librarymanagement.library.repository.NotificationRepository;
import librarymanagement.library.repository.UserRepository;
import librarymanagement.library.security.util.SecurityUtil;
import librarymanagement.library.service.NotificationService;
import librarymanagement.library.view.NotificationListView;

@Service
public class NotifictationServiceimpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public NotificationListView add(NotificationForm form) {
        Book book = bookRepository.findByBookId(form.getBookId());
        return new NotificationListView(notificationRepository.save(new Notification(form, book)));
    }

    @Override
    public Collection<Notification> listAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Collection<Notification> listbyId() {
        User user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new);
        return notificationRepository.findbyrecipentId(user.getUserId());
    }

    @Override
    public void aad(NotificationForm form) {
        Book book = bookRepository.findByBookId(form.getBookId());
        User user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new);
        Collection<User> reuser = userRepository.findAll();
        Date dt = new Date();
        byte s = 1;
        byte r = 0;
        for (User i : reuser) {
            Notification a = new Notification();
            a.setRecipentId(i.getUserId());
            a.setSenderId(user.getUserId());
            a.setMessage(form.getMessage());
            a.setBook(book);
            a.setCreateDate(dt);
            a.setStatus(s);
            a.setReadStatus(r);
            notificationRepository.save(a);

        }

    }
    @Override
    public void deleteAllbyUserid() {
        User user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new);
        Collection<Notification> notifi = notificationRepository.findbyrecipentId(user.getUserId());
        for (Notification i : notifi) {
          
            if(user.getUserId().equals(i.getRecipentId())){
                Notification notification=notificationRepository.findById(i.getNotifiId()).orElseThrow(NotFoundException::new);

            notificationRepository.save(notification.update());
            }

        }

    }
    @Override
    @Transactional
    public void readNotification( Integer notId){
        Notification notification=notificationRepository.findById(notId).orElseThrow(NotFoundException::new);
        byte s=1;
        notification.setReadStatus(s);
    }

}
