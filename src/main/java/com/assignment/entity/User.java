package com.assignment.entity;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

import javax.validation.constraints.Email;
import java.util.List;

@Entity
public class User {
    @Id
    @Email
    private String email;
    private String password;
    private String salt;
    private String fullname;
    private String avatar;
    @Index
    @Load
    private List<Ref<ActivationCode>> activationCodes;
    private int status; // 0 = INACTIVE, 1 = ACTIVE

    public User() {
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

    public List<Ref<ActivationCode>> getActivationCodes() {
        return activationCodes;
    }

    public void setActivationCodes(List<Ref<ActivationCode>> activationCodes) {
        this.activationCodes = activationCodes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static final class Builder {
        private String email;
        private String password;
        private String salt;
        private String fullname;
        private String avatar;
        private List<Ref<ActivationCode>> activationCodes;
        private int status;

        private Builder() {
        }

        public static Builder anUser() {
            return new Builder();
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withSalt(String salt) {
            this.salt = salt;
            return this;
        }

        public Builder withFullname(String fullname) {
            this.fullname = fullname;
            return this;
        }

        public Builder withAvatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public Builder withActivationCodes(List<Ref<ActivationCode>> activationCodes) {
            this.activationCodes = activationCodes;
            return this;
        }

        public Builder withStatus(int status) {
            this.status = status;
            return this;
        }

        public User build() {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setSalt(salt);
            user.setFullname(fullname);
            user.setAvatar(avatar);
            user.setActivationCodes(activationCodes);
            user.setStatus(status);
            return user;
        }
    }
}
