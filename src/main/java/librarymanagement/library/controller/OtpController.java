package librarymanagement.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import librarymanagement.library.form.OtpForm;
import librarymanagement.library.service.OtpService;

@RestController
@RequestMapping("/verify")
public class OtpController {
    @Autowired
    private OtpService otpService;

    @PostMapping()
    public boolean add(@RequestBody OtpForm form){
        System.out.println("????????????????"+form.getEmail());
        System.out.println("........."+form.getCnewPassword()+form.getNewPassword());
        return otpService.add(form);
    }

   
}
