package librarymanagement.library.controller;


import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import librarymanagement.library.entity.Otp;
import librarymanagement.library.form.EmailForm;
import librarymanagement.library.repository.OtpRepository;
import librarymanagement.library.service.EmailService;
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private OtpRepository otpRepository;

    @PostMapping("/emailsent")
    public ResponseEntity<?>sendEmail(@RequestBody EmailForm form){
        boolean result = this.emailService.sendEmail("Nan dhaa shandhappan","njn email ", form.getSentto());
        // "OTP Verification", "Your OTP to change your password is "+"otp"+"use it to create a new password."
        if(result){
            return  ResponseEntity.ok("Email Sent!");
        }else{
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not sent.");
        }
    }

    @PostMapping("/emailsentotp")
    public ResponseEntity<?>sendOtpEmail(@RequestBody EmailForm form){
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        Otp otp2= new Otp();
        otp2.setOtp(otp);
        otp2.setEmail(form.getSentto());
        otpRepository.deleteAll();
        otpRepository.save(otp2);
        boolean result = this.emailService.sendEmail("OTP Verification","Your OTP to change your password is \t"+ otp +"\tuse it to create a new password.", form.getSentto());
        // "OTP Verification", "Your OTP to change your password is "+"otp"+"use it to create a new password."
        if(result){
            return  ResponseEntity.ok("Email Sent!");
        }else{
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not sent.");
        }
    }
    
}
