package librarymanagement.library.form;

import  librarymanagement.library.form.validation.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class UserForm {
    @NotBlank
    @Size(max = 250)
    private String name;
    @NotBlank
    @Size(max = 255)
    @Email
    private String email;
    @Password
    private String password;
    @NotBlank
    @Size(max = 255)
    private String address;
    @NotBlank
    @Size(max = 255)
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress(){
        return address;
    }
    
    public void setAddress(String address){
        this.address=address;
    }

    public String getPhone(){ 
    return phone;
    }

    public void setPhone(String phone){
        this.phone=phone;
    }

    
}
    