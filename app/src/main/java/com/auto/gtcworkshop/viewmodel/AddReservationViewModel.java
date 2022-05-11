package com.auto.gtcworkshop.viewmodel;

import androidx.lifecycle.ViewModel;

import com.auto.gtcworkshop.model.Reservation;
import com.auto.gtcworkshop.model.User;
import com.auto.gtcworkshop.repository.ReservationRepository;

public class AddReservationViewModel extends ViewModel {
    private ReservationRepository reservationRepository;

    public AddReservationViewModel(){
        reservationRepository = ReservationRepository.getInstance();
    }

    public void addReservation(Reservation reservation){
        reservationRepository.addReservation(reservation);
    }
}
