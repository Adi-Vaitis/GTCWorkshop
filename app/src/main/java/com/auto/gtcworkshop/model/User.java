package com.auto.gtcworkshop.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String fullName;
    private String phone;
    private String email;
    private String password;
    private List<Reservation> reservations;

    public User() {
        reservations = new ArrayList<>();
    }


    public User(String email, String password, String fullName,String phone)
    {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.phone = phone;
    }
    public User (String email, String password)
    {this.email=email;
    this.password = password;}
public User(String email, String password, String fullName,String phone, List<Reservation> reservations)
{
    this.email = email;
    this.fullName = fullName;
    this.password = password;
    this.phone = phone;
    this.reservations = reservations;
}

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", reservations=" + reservations +
                '}';
    }
}
