package com.assignment.entity;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
public class ActivationCode {
    @Id
    private String code;
    @Index
    private long expiredTime;
    @Index
    @Load
    private Ref<User> user;
    @Index
    private int status; // 0 = EXPIRED, 1 = CAN BE USE

    public ActivationCode() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Ref<User> getUser() {
        return user;
    }

    public void setUser(Ref<User> user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static final class Builder {
        private String code;
        private long expiredTime;
        private Ref<User> user;
        private int status; // 0 = EXPIRED, 1 = CAN BE USE

        private Builder() {
        }

        public static Builder anActivationCode() {
            return new Builder();
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public Builder withExpiredTime(long expiredTime) {
            this.expiredTime = expiredTime;
            return this;
        }

        public Builder withUser(Ref<User> user) {
            this.user = user;
            return this;
        }

        public Builder withStatus(int status) {
            this.status = status;
            return this;
        }

        public boolean isExpired(){

            return true;
        }

        public ActivationCode build() {
            ActivationCode activationCode = new ActivationCode();
            activationCode.setCode(code);
            activationCode.setExpiredTime(expiredTime);
            activationCode.setUser(user);
            activationCode.setStatus(status);
            return activationCode;
        }
    }
}
