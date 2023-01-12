package librarymanagement.library.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public NotificationListView add(@Valid @RequestBody NotificationForm form ){
        // System.out.println(form);
        return notificationService.add(form);
    
    }
    @GetMapping("/viewmsg")
    public Collection<Notification> get(){
        return notificationService.listAll();
    }
    
}
