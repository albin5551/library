package librarymanagement.library.view;

import librarymanagement.library.entity.User;
import librarymanagement.library.json.Json;
import java.util.Date;

public class UserView {
    
    private final int userId;
    private final String name;
    private final String email;
    private final String address;
    private final String phone;
    private final byte role;


    private final byte status;
    private final String password;
    @Json.DateTimeFormat
    private final Date createDate;
    @Json.DateTimeFormat
    private final Date updateDate;


    public UserView(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password=user.getPassword();
        this.address=user.getAddress();
        this.phone=user.getPhone();
        this.role=user.getRole();
        this.status = user.getStatus();
        this.createDate = user.getCreateDate();
        this.updateDate = user.getUpdateDate();
    }


    public int getUserId() {
        return userId;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String getAddress(){
        return address;
    }
    public String getPhone(){
        return phone;
    }
    public byte getStatus() {
        return status;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public byte getRole() {
        return role;
    }

}
