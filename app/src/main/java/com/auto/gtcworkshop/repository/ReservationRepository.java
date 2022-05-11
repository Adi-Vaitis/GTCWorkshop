package com.auto.gtcworkshop.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.auto.gtcworkshop.livedata.FirebaseUserLiveData;
import com.auto.gtcworkshop.livedata.ReservationsLiveData;
import com.auto.gtcworkshop.model.Reservation;
import com.auto.gtcworkshop.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ReservationRepository {
    private static ReservationRepository instance;
    private ReservationsLiveData reservationsLiveData;
    private User user = null;

    private FirebaseFirestore firebaseDatabase;
    private final FirebaseUserLiveData currentFirebaseUser;


    public static ReservationRepository getInstance(){
        if(instance == null) instance = new ReservationRepository();
        return instance;
    }

    public ReservationRepository(){
        firebaseDatabase = FirebaseFirestore.getInstance();
        currentFirebaseUser = new FirebaseUserLiveData();
    }

    public void addReservation(Reservation reservation){
        Map<String, Object> reservationMap = new HashMap<>();
        if(reservation.getUserId() != null){
            reservationMap.put("userId", reservation.getUserId());
            reservationMap.put("feel", reservation.getFeel());
            reservationMap.put("model", reservation.getModel());
            reservationMap.put("problem", reservation.getProblem());
            reservationMap.put("millage", reservation.getMillage());

            firebaseDatabase.collection("Reservations").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(reservationMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d(TAG, "DocumentSnapshot successfully written!");
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                        }
                    });
        }

    }

    public void init(){
        reservationsLiveData = new ReservationsLiveData(firebaseDatabase.collection("reservations").document(FirebaseAuth.getInstance().getCurrentUser().getUid()));
    }

    public ReservationsLiveData getReservationsLiveData(){
        return reservationsLiveData;
    }




}
