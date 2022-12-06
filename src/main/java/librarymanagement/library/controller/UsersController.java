package librarymanagement.library.controller;

import librarymanagement.library.entity.User;
import librarymanagement.library.form.UserForm;
import librarymanagement.library.service.UserService;
import librarymanagement.library.view.UserView;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/users")
public class UsersController{

    @Autowired
    private UserService userService;

    @PostMapping
    public UserView add(@Valid @RequestBody UserForm form) {
        return userService.add(form);
    }
    @GetMapping
    public Collection<User>list(){
        return userService.list();
    }

    @PutMapping
    public UserView update( @Valid @RequestBody UserForm form) 
    {
        return userService.update(form);
    }

    @PutMapping("/{userId}")
    public UserView update(
            @PathVariable("userId") Integer userId,
            @Valid @RequestBody UserForm form
    ) {
        return userService.update(userId, form);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") Integer userId) {
        userService.delete(userId);
    }

    @PatchMapping("/{userId}")
    public UserView updates(@PathVariable Integer userId, @RequestBody UserForm form)
    {
      
        return userService.updates(form,userId); 
    }


    @GetMapping("/{userId}")
    public UserView get(@PathVariable("userId") Integer userId) {
        return userService.get(userId);
    }
    
    
}
