package com.auto.gtcworkshop.repository;
import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.auto.gtcworkshop.livedata.FirebaseUserLiveData;
import com.auto.gtcworkshop.livedata.UserLiveData;
import com.auto.gtcworkshop.model.Reservation;
import com.auto.gtcworkshop.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ReservationRepository {
    private static ReservationRepository instance;
private AuthentificationRepository userRes;

    private final FirebaseFirestore fStore;

    public static ReservationRepository getInstance()
    {
        if(instance == null)
        {
            instance = new ReservationRepository();
        }
        return instance;
    }

    public ReservationRepository ()
    {
        userRes = AuthentificationRepository.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }

    public void createReservation(Reservation reservation)
    {
            Map<String, Object> reservation1 = new HashMap<>();
        reservation1.put("Fell", reservation.getFeel());

        reservation1.put("Model", reservation.getModel());

        reservation1.put("Millage", reservation.getMillage());

        reservation1.put("Problem", reservation.getProblem());



        fStore.collection("Reservations").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(reservation1).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d(TAG, "Reservation has been created in the Database");
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                        }
                    });


    }

    public void deleteReservation(Reservation reservation)
    {

       reservation1 = fStore.collection("Reservations").document();


        reservation1
                .update("Problem", "Canceled")
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "The Reservation has been set as canceled");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });


    }




}

