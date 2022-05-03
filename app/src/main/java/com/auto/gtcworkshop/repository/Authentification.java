package com.auto.gtcworkshop.repository;

public class Authentification {
    private String userId;
    private String fullName;
    private String email;
    private String phoneNo;
    private String password;

    public Authentification(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        fullName = null;
        phoneNo = null;
    }

    public Authentification(String userId, String fullName, String email, String phoneNo, String password) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
