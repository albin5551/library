package librarymanagement.library.service.impl;

import java.text.Normalizer.Form;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import librarymanagement.library.entity.Book;
import librarymanagement.library.entity.Request;
import librarymanagement.library.entity.User;
import librarymanagement.library.exception.NotFoundException;
import librarymanagement.library.form.RequestForm;
import librarymanagement.library.repository.BookRepository;
import librarymanagement.library.repository.RequestRepository;
import librarymanagement.library.repository.UserRepository;
import librarymanagement.library.security.util.SecurityUtil;
import librarymanagement.library.service.RequestServic;
import librarymanagement.library.view.RequestListview;

@Service
public class RequestServiceImpl implements RequestServic {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Collection<Request> list() {
        return requestRepository.findAll();
    }

    @Override
    public RequestListview add(RequestForm form) {
        User user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new);
        Book book = bookRepository.findByBookId(form.getBookId());

        return new RequestListview(requestRepository.save(new Request(user, book)));
    }

    // @Override
    // @Transactional
    // public RequestListview update(Integer reqId, RequestForm form) throws NotFoundException {
    //     Request req=requestRepository.findById(reqId);
    //     User user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new);
    //     Book book = bookRepository.findByBookId(form.getBookId());
    //     req.setBook(book);
    //     req.setUser(user);
    //     requestRepository.save(req);
    //     return new RequestListview(req);

    // }
    @Override
    @Transactional
    public RequestListview update(Integer requestId, RequestForm form) throws NotFoundException {
   Request request=requestRepository.findById(requestId).orElseThrow(NotFoundException::new);
   User user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new);
   Book book = bookRepository.findByBookId(form.getBookId());
   request.setBook(book);
   request.setUser(user);
//    byte a=0;
//    request.setStatus(a);
   return new RequestListview(
                            requestRepository.save(request.update( user, book)));
   
    }
}
