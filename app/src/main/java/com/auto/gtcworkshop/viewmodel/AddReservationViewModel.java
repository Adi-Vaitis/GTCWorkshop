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

    public boolean addReservation(String feel, String model, String problem, String millage){
        Reservation reservation = new Reservation(feel, model, problem, millage);
        reservationRepository.addReservation(reservation);
        return true;
    }
}
