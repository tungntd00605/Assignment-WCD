package com.assignment.dto;

import com.assignment.entity.User;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private String email;
    private String password;
    private String salt;
    private String fullname;
    private String avatar;
    private int status; // 0 = INACTIVE, 1 = ACTIVE

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.salt = user.getSalt();
        this.fullname = user.getFullname();
        this.avatar = user.getAvatar();
        this.status = user.getStatus();
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
