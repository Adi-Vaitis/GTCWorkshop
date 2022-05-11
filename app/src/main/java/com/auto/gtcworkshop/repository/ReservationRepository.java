package com.auto.gtcworkshop.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.auto.gtcworkshop.model.Reservation;
import com.auto.gtcworkshop.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ReservationRepository {
    private static ReservationRepository instance;
    private AuthentificationRepository authentificationRepository;
    private User user = null;

    private FirebaseFirestore firebaseDatabase;

    public static ReservationRepository getInstance(){
        if(instance == null) instance = new ReservationRepository();
        return instance;
    }

    public ReservationRepository(){
        authentificationRepository = AuthentificationRepository.getInstance();
        firebaseDatabase = FirebaseFirestore.getInstance();
    }

    public void addReservation(Reservation reservation, User user){
        Map<String, Object> reservationMap = new HashMap<>();
        if(reservation.getUserId() != null){
            reservationMap.put("userId", reservation.getUserId());
            reservationMap.put("feel", reservation.getFeel());
            reservationMap.put("model", reservation.getModel());
            reservationMap.put("problem", reservation.getProblem());
            reservationMap.put("millage", reservation.getMillage());
            int position = user.getReservations().size() - 1;
            firebaseDatabase.collection("reservations").document(user.getFullName() + "[" + position + "]" + reservation.getUserId())
                    .set(reservationMap)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "Reservation added successfully!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding reservation", e);
                        }
                    });
        }
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return authentificationRepository.getCurrentFirebaseUser();
    }


}
