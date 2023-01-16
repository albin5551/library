package librarymanagement.library.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import librarymanagement.library.entity.Notification;
import librarymanagement.library.form.NotificationForm;
import librarymanagement.library.service.NotificationService;
import librarymanagement.library.view.NotificationListView;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PostMapping("/addmsg")
    public NotificationListView add(@Valid @RequestBody NotificationForm form) {
        // System.out.println(form);
        return notificationService.add(form);

    }

    @GetMapping("/viewmsgs")
    public Collection<Notification> get() {
        return notificationService.listAll();
    }

    @PostMapping("/addmsgs")
    public void aad(@Valid @RequestBody NotificationForm form) {
        // System.out.println(form);
        notificationService.aad(form);

    }

    @GetMapping("/viewmsg")
    public Collection<Notification> getbyId() {
        return notificationService.listbyId();
    }

    @DeleteMapping("/read")
    void read() {
        System.out.println("+++");
        notificationService.deleteAllbyUserid();
    }

}
