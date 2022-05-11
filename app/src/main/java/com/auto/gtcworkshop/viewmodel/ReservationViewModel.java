package com.auto.gtcworkshop.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.auto.gtcworkshop.model.Reservation;
import com.auto.gtcworkshop.model.User;
import com.auto.gtcworkshop.repository.ReservationRepository;
import com.google.firebase.auth.FirebaseUser;

public class ReservationViewModel extends ViewModel {
    private ReservationRepository reservationRepository;


    public ReservationViewModel(){
        reservationRepository = ReservationRepository.getInstance();
    }

    public void addReservation(Reservation reservation, User user){
        reservationRepository.addReservation(reservation, user);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return reservationRepository.getCurrentUser();
    }
}
