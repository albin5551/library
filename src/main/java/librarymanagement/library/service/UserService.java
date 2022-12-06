package librarymanagement.library.service;

import librarymanagement.library.entity.User;
import librarymanagement.library.exception.BadRequestException;
import librarymanagement.library.exception.NotFoundException;
import librarymanagement.library.form.LoginForm;
import librarymanagement.library.form.UserForm;
import librarymanagement.library.view.LoginView;
import librarymanagement.library.view.UserView;
import java.util.Collection;
import org.springframework.validation.Errors;

public interface UserService {
    UserView add(UserForm form);

    UserView currentUser();

    LoginView login(LoginForm form, Errors errors) throws BadRequestException;

    LoginView refresh(String refreshToken) throws BadRequestException;

    UserView update(Integer userId, UserForm form) throws NotFoundException;

    UserView update(UserForm form) throws NotFoundException;

    void delete(Integer userId) throws NotFoundException;

    Collection<User> list();
    UserView get(Integer userId)throws NotFoundException;

    UserView updates(UserForm form,Integer userId)throws NotFoundException;
}
