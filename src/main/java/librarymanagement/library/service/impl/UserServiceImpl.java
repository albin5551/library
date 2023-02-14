package librarymanagement.library.service.impl;

import librarymanagement.library.entity.User;
import librarymanagement.library.exception.BadRequestException;
import librarymanagement.library.exception.NotFoundException;
import librarymanagement.library.form.LoginForm;
import librarymanagement.library.form.UserForm;
import librarymanagement.library.repository.UserRepository;
import static librarymanagement.library.security.AccessTokenUserDetailsService.PURPOSE_ACCESS_TOKEN;
import librarymanagement.library.security.config.SecurityConfig;
import librarymanagement.library.security.util.InvalidTokenException;
import librarymanagement.library.security.util.SecurityUtil;
import librarymanagement.library.security.util.TokenExpiredException;
import librarymanagement.library.security.util.TokenGenerator;
import librarymanagement.library.security.util.TokenGenerator.Status;
import librarymanagement.library.security.util.TokenGenerator.Token;
import librarymanagement.library.service.UserService;
import librarymanagement.library.view.LoginView;
import librarymanagement.library.view.UserView;

import java.util.Collection;
import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
public class UserServiceImpl implements UserService {

    private static final String PURPOSE_REFRESH_TOKEN = "REFRESH_TOKEN";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private SecurityConfig securityConfig;

    @Override
    public UserView add(UserForm form) {

       Optional< User> user= userRepository.findByEmail(form.getEmail());
       if(user.isPresent()){    
       return null;
       }
        return new UserView(userRepository.save(new User(
                form.getName(),
                form.getEmail(),
                form.getAddress(),
                form.getPhone(),
                passwordEncoder.encode(form.getPassword()))));
    }

    @Override
    public UserView currentUser() {
        return new UserView(
                userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new));
    }

    @Override
    public LoginView login(LoginForm form, Errors errors) throws BadRequestException {
        if (errors.hasErrors()) {
            throw badRequestException();
        }
        User user = userRepository.findByEmail(form.getEmail()).orElseThrow(UserServiceImpl::badRequestException);
        if (!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
            throw badRequestException();
        }

        String id = String.format("%010d", user.getUserId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        Token refreshToken = tokenGenerator.create(PURPOSE_REFRESH_TOKEN, id + user.getPassword(),
                securityConfig.getRefreshTokenExpiry());
        return new LoginView(user, accessToken, refreshToken);
    }

    @Override
    public LoginView refresh(String refreshToken) throws BadRequestException {
        Status status;
        try {
            status = tokenGenerator.verify(PURPOSE_REFRESH_TOKEN, refreshToken);
        } catch (InvalidTokenException e) {
            throw new BadRequestException("Invalid token", e);
        } catch (TokenExpiredException e) {
            throw new BadRequestException("Token expired", e);
        }

        int userId;
        try {
            userId = Integer.parseInt(status.data.substring(0, 10));
        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid token", e);
        }

        String password = status.data.substring(10);

        User user = userRepository.findByUserIdAndPassword(userId, password)
                .orElseThrow(UserServiceImpl::badRequestException);

        String id = String.format("%010d", user.getUserId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        return new LoginView(
                user,
                new LoginView.TokenView(accessToken.value, accessToken.expiry),
                new LoginView.TokenView(refreshToken, status.expiry));
    }

    private static BadRequestException badRequestException() {
        return new BadRequestException("Invalid credentials");
    }

    @Override
    public Collection<User> list() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public UserView update(UserForm form) throws NotFoundException {
        User user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new);
        user.setName(form.getName());
        user.setEmail(form.getEmail());
        user.setAddress(form.getAddress());
        user.setPhone(form.getPhone());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        userRepository.save(user);
        return new UserView(user);
    }

    @Override
    @Transactional
    public UserView update(Integer userId, UserForm form) throws NotFoundException {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        user.setName(form.getName());
        user.setEmail(form.getEmail());
        user.setAddress(form.getAddress());
        user.setPhone(form.getPhone());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        userRepository.save(user);
        return new UserView(user);

    }

    @Override
    @Transactional
    public void delete(Integer userId) throws NotFoundException {
        User user=userRepository.findById(userId).orElseThrow(NotFoundException::new);
        user.setStatus(User.Status.INACTIVE.value);
        userRepository.save(user);  
    }




   @Override
    public UserView get(Integer userId) throws NotFoundException {
        return userRepository.findById(userId)
                .map((user) -> {
                    return new UserView(user);
                }).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public UserView updates(UserForm form,Integer userId)throws NotFoundException{
        User user=userRepository.findById(userId).orElseThrow(NotFoundException::new);
        user.setName(form.getName());
        user.setEmail(form.getEmail());
        user.setAddress(form.getAddress());
        user.setPhone(form.getPhone());
        userRepository.save(user);
        return new UserView(user);


    }

    

}
