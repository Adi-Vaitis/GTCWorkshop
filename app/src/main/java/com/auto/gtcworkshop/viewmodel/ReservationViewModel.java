package com.auto.gtcworkshop.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.auto.gtcworkshop.livedata.ReservationsLiveData;
import com.auto.gtcworkshop.model.Reservation;
import com.auto.gtcworkshop.model.User;
import com.auto.gtcworkshop.repository.ReservationRepository;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ReservationViewModel extends ViewModel {
    private ReservationRepository reservationRepository;


    public ReservationViewModel(){
        reservationRepository = ReservationRepository.getInstance();
    }

    public void addReservation(Reservation reservation){
        reservationRepository.addReservation(reservation);
    }

    public void init(){
        reservationRepository.init();
    }

    public ReservationsLiveData getReservationLiveData(){
        return reservationRepository.getReservationsLiveData();
    }

    public MutableLiveData<ArrayList<Reservation>> getReservationsLiveData(){
        return reservationRepository.getReservationLiveData();
    }

    public void getReservations(){
        reservationRepository.getReservations();
    }
}
