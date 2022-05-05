package com.auto.gtcworkshop.repository;

import java.util.ArrayList;

public class User {
    private String uid;
    private String fullName;
    private String email;
    private ArrayList<String> reservations;
    private String phone;

    public User(String uid, String email)
    {
        this.uid = uid;
        this.email = email;
        reservations = new ArrayList<>();
        fullName = null;
        phone = null;
    }

    public User (String uid, String email, String phone, ArrayList<String> reservations, String fullName)
    { this.uid = uid;
        this.phone = phone;
        this.email = email;
        this.reservations = reservations;
        this.fullName = fullName;
       }
       public User(){}

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<String> reviews) {
        this.reservations = reservations;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", reservations=" + reservations +
                ", fullName='" + fullName + '\'' +
                ", phone=" + phone +
                '}';
    }


}
