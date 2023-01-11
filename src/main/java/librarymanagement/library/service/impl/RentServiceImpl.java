package librarymanagement.library.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
import librarymanagement.library.view.RentCharView;
import librarymanagement.library.view.RentListView;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Collection<Rent> list() {
        return rentRepository.findAll();
    }

    @Override
    public List<Rent> listcsv() {
        return rentRepository.findAllC();
    }

    @Override
    public List<Rent> listCsvSerach(String keyword) throws NotFoundException {
        return rentRepository.findBySer(keyword);
    }

    @Override
    public RentListView add(RentForm form) {
        Book book = bookRepository.findByBookId(form.getBookId());
        User user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new);
        book.setStock(book.getStock() - 1);
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
    public RentListView update(Integer rentId, RentForm form) throws NotFoundException {
        return rentRepository.findById(rentId)
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
    public RentListView rentApprove(Integer rentId, RentForm form) throws NotFoundException {
        return rentRepository.findById(rentId)
                .map((rent) -> {
                    Book book = bookRepository.findByBookId(form.getBookId());
                    User user = userRepository.findById(form.getUserId())
                            .orElseThrow(NotFoundException::new);
                    book.setStock(book.getStock() + 1);
                    return new RentListView(
                            rentRepository.save(rent.returnApprove(form, user, book)));
                }).orElseThrow(NotFoundException::new);

    }

    @Override
    @Transactional
    public void delete(Integer orderId) throws NotFoundException {
        bookRepository.delete(
                bookRepository.findById(orderId).orElseThrow()

        );
    }

    @Override
    public Collection<Rent> list1() {
        return rentRepository.findAllByUserUserId(SecurityUtil.getCurrentUserId());
    }

    public Page<Rent> getAllRent(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Rent> pagedResult = rentRepository.findAll(paging);
        return pagedResult;
        // if (pagedResult.hasContent()){
        // return pagedResult.getContent();
        // }else{
        // return new ArrayList<Rent>();
        // }
    }

    public Page<Rent> getAllRentKey(String keyword, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Rent> pagedResult = rentRepository.findByKey(keyword, paging);
        return pagedResult;
    }
    // public List<Rent>listBetweenDates(){
    // LocalDateTime ed=LocalDateTime.now();
    // System.out.println(ed);
    // LocalDateTime st=LocalDateTime.now().minusDays(7);
    // System.out.println(st);

    // return rentRepository.findBybetweenDate();
    // }

    public RentCharView getChart() {
        RentCharView result = new RentCharView();
        String[] weeks = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        result.setLabel(Arrays.asList(weeks));

        HashMap<Integer, Result> hm = new HashMap<Integer, Result>();

        hm.put(1, new Result(0, 0));
        hm.put(2, new Result(0, 0));
        hm.put(3, new Result(0, 0));
        hm.put(4, new Result(0, 0));
        hm.put(5, new Result(0, 0));
        hm.put(6, new Result(0, 0));
        hm.put(7, new Result(0, 0));

        List<Rent> s = rentRepository.findAllC();

        for (Rent a : s) {
            LocalDateTime b = a.getRentDate();
            hm.put(b.getDayOfWeek().getValue(), new Result(hm.get(b.getDayOfWeek().getValue()).getRentCount() + 1,
                    hm.get(b.getDayOfWeek().getValue()).getReturnCount()));
            LocalDateTime c = null;
            if (a.getStatus().equals("1")) {
                c = a.getReturnDate();

                hm.put(c.getDayOfWeek().getValue(), new Result(hm.get(c.getDayOfWeek().getValue()).getRentCount(),
                        hm.get(c.getDayOfWeek().getValue()).getReturnCount() + 1));

                System.out.println(hm.get(c.getDayOfWeek().getValue()).getReturnCount());
            }

            // System.out.println(b.getDayOfWeek().getValue());
            // System.out.println(hm.get(b.getMonth().getValue()).getReturnCount());

        }
        for (Map.Entry<Integer, Result> mapElement : hm.entrySet()) {
            result.getRentCount().add(mapElement.getValue().getRentCount() + "");
            result.getReturnCount().add(mapElement.getValue().getReturnCount() + "");

        }
        return result;

    }

    public class Result {
        private Integer rentCount;
        private Integer returnCount;

        public Result(Integer rentCount, Integer returnCount) {
            this.rentCount = rentCount;
            this.returnCount = returnCount;
        }

        public Integer getRentCount() {
            return rentCount;
        }

        public void setRentCount(Integer rentCount) {
            this.rentCount = rentCount;
        }

        public Integer getReturnCount() {
            return returnCount;
        }

        public void setReturnCount(Integer returnCount) {
            this.returnCount = returnCount;
        }

    }

    @Override
    @Transactional
    // @Scheduled(cron="*/10 * * * * *")
    // @Scheduled(cron = "0 */1 * 1/1 * ? ")
    @Scheduled(cron="0 0 12 * * ?")
    public void sendMails() {
        System.out.println("Email send");
        Collection<Rent> rent = rentRepository.findbyDueDate();
        for (Rent r : rent) {
            User user = userRepository.findbyuserId(r.getUser().getUserId());
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom("stormhokspam@gmail.com");
            simpleMailMessage.setTo(user.getEmail());
            simpleMailMessage.setSubject("Book Due");
            simpleMailMessage.setText("The" + r.getBook().getBookName()
                    + "due date is tomorrow please return book on tomorrow of fine will be charged");

            this.mailSender.send(simpleMailMessage);
        }

    }

    @Override
    @Transactional
        // @Scheduled(cron="*/10 * * * * *")
    // @Scheduled(cron = "0 */1 * 1/1 * ? ")
    @Scheduled(cron="0 0 12 * * ?")
    public void fineGen(){
        System.out.println("Fine gen");
        Collection<Rent> rent = rentRepository.findbyDueDateFine();
        for(Rent r:rent){
            System.out.println(r.getRentId());
            Date d=new Date();
            Long due=d.getTime()-r.getDueDate().getTime(); //date conversion to time
            // System.out.println(due);
            due=due/86400000; //time conversion to dat
            // System.out.println(due);
            r.setDueDays(due);
            r.setFine(due*5);
        }

    }
}
