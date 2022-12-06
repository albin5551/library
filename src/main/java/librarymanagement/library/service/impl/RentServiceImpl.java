package librarymanagement.library.service.impl;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import librarymanagement.library.entity.Book;
import librarymanagement.library.entity.Rent;
import librarymanagement.library.entity.User;
import librarymanagement.library.exception.NotFoundException;
import librarymanagement.library.form.RentForm;
import librarymanagement.library.repository.BookRepository;
import librarymanagement.library.repository.RentRepository;
import librarymanagement.library.repository.UserRepository;
import librarymanagement.library.security.util.SecurityUtil;
import librarymanagement.library.service.RentService;
import librarymanagement.library.view.RentListView;

public class RentServiceImpl implements RentService {
    

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Collection<Rent>list() {
        return rentRepository.findAll();
    }

    @Override
    public RentListView add(RentForm form) {
        Book book = bookRepository.findByBookId(form.getBookId());
        User user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new);
        return new RentListView(rentRepository.save(new Rent(form, user, book)));
    }

    @Override
    public RentListView get(Integer rentId) throws NotFoundException {
        return rentRepository.findById(rentId)
                .map((rent) -> {
                    return new RentListView(rent);
                }).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public RentListView update(Integer orderId, RentForm form) throws NotFoundException {
        return rentRepository.findById(orderId)
                .map((rent) -> {
                    Book book = bookRepository.findByBookId(form.getBookId());
                    User user = userRepository.findById(SecurityUtil.getCurrentUserId())
                            .orElseThrow(NotFoundException::new);
                    return new RentListView(
                            rentRepository.save(rent.update(form, user, book)));
                }).orElseThrow(NotFoundException::new);
    }


    @Override
@Transactional
public void delete(Integer orderId) throws NotFoundException {
    bookRepository.delete(
        bookRepository.findById(orderId).orElseThrow()

    );
}

}

    

