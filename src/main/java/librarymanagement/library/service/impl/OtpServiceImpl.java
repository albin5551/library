package librarymanagement.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import librarymanagement.library.entity.Otp;
import librarymanagement.library.entity.User;
import librarymanagement.library.form.OtpForm;
import librarymanagement.library.repository.OtpRepository;
import librarymanagement.library.repository.UserRepository;
import librarymanagement.library.service.OtpService;

public class OtpServiceImpl implements OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean add(OtpForm form){
        Otp otp=otpRepository.findByEmail(form.getEmail());
        User user=userRepository.findByEmailId(form.getEmail());
        if ((form.getOtp().equals(otp.getOtp()))) {

            if (form.getNewPassword().equals(form.getCnewPassword()))

            {
                
                user.setPassword(passwordEncoder.encode(form.getNewPassword()));
                userRepository.save(user);
                return true;
            } 
            return false;
            

        }
            return false;
    }
    
    
}
